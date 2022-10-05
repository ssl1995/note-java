import Taro, { FC, useState, useEffect } from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtRadio, AtButton, AtToast } from 'taro-ui'
import api from '../../services/api'
import './index.less'

interface postDataItem {
    questionId?: string,
    question?: string,
    answer?: string
}
type reportData = Array<{
    questionId: string,
    question: string,
    answer: string,
    options: Array<{
        label: string,
        value: string
    }>
}>

const Page: FC = () => {
    const [ templateId, setTemplateId ] = useState<string>('')
    const [ reportData, setReportData ] = useState<reportData>([])
    const [ btnLoading, setBtnLoading ] = useState<boolean>(false)
    const [ pageLoading, setPageLoading ] = useState<boolean>(true)

    useEffect(() => {
        api.get('/v1/template', {
        }).then((res) => {
            setPageLoading(false)
            setReportData(res.data.result.template)
            setTemplateId(res.data.result.templateId)
        }) 
    }, [])

    function handleChange(value, questionId) {
        setReportData(reportData.map((item) => {
            if (item.questionId === questionId) {
                item.answer = value
            }
            return item
        }))
    }
    function submit() {
        if (btnLoading) return
        let flag = true
        reportData.forEach((item) => {
            if (!item.answer) {
                flag = false
                Taro.showToast({
                    title: '请全部填完再提交',
                    icon: 'none',
                    duration: 1000
                })
            }
        })
        if (!flag) return
        const postData = reportData.map((item) => {
            const { questionId, question, answer } = item
            let tempItem: postDataItem = {}
            tempItem.questionId = questionId
            tempItem.question = question
            tempItem.answer = answer
            return tempItem
        })
        setBtnLoading(true)
        console.log('postData =>', postData)
        api.post('/v1/template/report', {
            templateId,
            result: postData
        }).then(() => {
            setBtnLoading(false)
            Taro.showToast({
                title: '提交成功',
                icon: 'success',
                duration: 1000
            })
            setTimeout(() => {
                Taro.redirectTo({
                    url: '/pages/result/index'
                })
            }, 1000) 
        })
    }
    return (
        <View className="report_container">
            <View className="report__title">问卷标题</View>
            <View className="report__content">
                {
                    reportData.map((item, index) => (
                        <View key={item.questionId} className="report__content__item">
                            <View className="report__content__item__title">{index + 1}、{item.question}</View>
                            <AtRadio
                                options={item.options}
                                value={item.answer}
                                onClick={(value) => handleChange(value, item.questionId)}
                            />
                        </View>
                    ))
                }
                <AtToast isOpened={pageLoading} text="加载中" status="loading" duration={0}/>
                <AtButton className='report__btn' loading={btnLoading}  type="primary" onClick={() => submit()}>提交</AtButton>
            </View>
        </View>
    )
}

Page.config = {
  navigationBarTitleText: '问卷调查'
}

export default Page

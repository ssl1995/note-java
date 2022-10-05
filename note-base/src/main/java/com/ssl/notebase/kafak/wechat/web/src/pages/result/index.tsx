import Taro, { FC, useState, useEffect } from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtProgress, AtButton } from 'taro-ui'
import api from '../../services/api'
import './index.less'

type resultData = Array<{
    questionId: string,
    question: string,
    answers: Array<{
        label: string,
        value: number,
        percent: number
    }>
}>

const Page: FC = () => {
    const [ resultData, setResultData ] = useState<resultData>([])
    const [ total, setTotal ] = useState<number>(0)
    useEffect(() => {
        api.get('/v1/template/result', {
            templateId: "001"
        }).then((res) => {
            setResultData(formatPercent(res.data.result.statistics))
            setTotal(res.data.result.totalNumber)
        })  
    }, [])

    function formatPercent(list) {
        return list.map((item) => {
            let tempTotal = 0
            item.answers.forEach((answer) => {
                tempTotal += answer.value
            })
            item.total = tempTotal
            item.answers.map((answer) => {
                answer.percent = (answer.value/tempTotal).toFixed(2)
                answer.percent = answer.percent * 100
            })
            return item
        })
    }

    function goHome() {
        Taro.reLaunch({
            url: '/pages/home/index'
        })
    }

    return (
        <View className="result_container">
            <View className="result__title">问卷结果</View>
            <View className="result__content">
                <View className="result__content__desc">投票总数：{total}</View>
                {
                    resultData.map((item, index) => (
                        <View key={item.questionId} className="result__content__item">
                            <View className="result__content__item__title">{index + 1}、{item.question}</View>
                            {
                                item.answers.map((answer) => (
                                    <View key={answer.label}>
                                        <Text>{answer.label}</Text>
                                        <View className="result__content__item__progress__wrap">
                                            <View className="result__content__item__progress">
                                                <AtProgress percent={answer.percent} strokeWidth={8} status="progress" isHidePercent={true} />
                                            </View>
                                            <Text className="primary_text">{answer.value}票</Text>
                                        </View>
                                    </View>
                                ))
                            }
                        </View>
                    ))
                }
                <AtButton className='result__btn' type="primary" onClick={() => goHome()}>返回首页</AtButton>
            </View>
        </View>
    )
}

Page.config = {
  navigationBarTitleText: '问卷结果'
}

export default Page

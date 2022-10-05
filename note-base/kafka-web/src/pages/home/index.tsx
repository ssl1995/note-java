import Taro, {FC, useState} from '@tarojs/taro'
import {Image, View} from '@tarojs/components'
import {AtButton} from 'taro-ui'
import './index.less'

interface userInfo {
    avatarUrl: string,
    nickName: string,
    gender: number,
    province: string,
    city: string,
    country: string
}

const Page: FC = () => {
  const [ userInfo ] = useState<userInfo>(Taro.getStorageSync('userInfo'))
  function goReport () {
    Taro.navigateTo({
        url: '/pages/report/index'
    })
  }
  return (
    <View className="home_container">
      <View className="home__userInfo">
        <Image src={userInfo.avatarUrl} className="home__userInfo__avatar"/>
        <View>{userInfo.nickName}</View>
      </View>
      <AtButton className='home__btn' type="primary" onClick={() => goReport()}>开始答卷</AtButton>
    </View>
  )
}

Page.config = {
  navigationBarTitleText: '调皮的问卷'
}

export default Page

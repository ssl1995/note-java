import Taro, {FC, useEffect} from '@tarojs/taro'
import {Image, View} from '@tarojs/components'
import {AtButton} from 'taro-ui'
import './index.less'

const Page: FC = () => {

  useEffect(() => {
    Taro.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          goHome()
        }
      }
    }).catch((err) => {
      console.log('err =>', err)
    })
  }, [])

  function getUserInfo(res) {
    if (res.detail.userInfo) {   // 同意
      Taro.setStorageSync('userInfo', res.detail.userInfo)
      goHome()
    }
  }

  function goHome() {
    Taro.redirectTo({
      url: '/pages/home/index'
    })
  }
  return (
    <View className="index_container">
      <View className="auth__content">
        <Image className="auth__content__img" src={require('../../assets/logo.jpg')}></Image>
        <View className='auth__content__title'>申请获取您的公开信息（昵称、头像等）</View>
        <AtButton openType='getUserInfo' onGetUserInfo={getUserInfo} type="primary" className='auth__content__btn'> 微信授权 </AtButton>
      </View>
    </View>
  )
}

Page.config = {
  navigationBarTitleText: '调皮的问卷'
}

export default Page

/*
 * @Description: In User Settings Edit
 * @Author: your name
 * @Date: 2019-08-29 10:30:27
 * @LastEditTime: 2019-08-29 10:45:25
 * @LastEditors: Please set LastEditors
 */
export const formatNumber = (n: number | string) : string => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

export const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}
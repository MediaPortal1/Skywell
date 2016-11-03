package ua.com.skywell.vk


interface VkModel {
    fun getUserInfo(callback: VkPresenterCallback)
    fun getUserWall(callback: VkPresenterCallback, offset: Int)
}
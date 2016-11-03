package ua.com.skywell.login

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback


interface LoginPresenter {
    fun onLoginBtnClick()
    fun getVkLoginCallback(): VKCallback<VKAccessToken>
    fun onLoginSuccessAction()
}
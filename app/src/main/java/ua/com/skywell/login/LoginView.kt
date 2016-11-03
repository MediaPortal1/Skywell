package ua.com.skywell.login


interface LoginView {
    fun onLoginSuccess(message: String)
    fun onLoginError(message: String)
}
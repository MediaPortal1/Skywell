package ua.com.skywell.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vk.sdk.VKSdk
import com.vk.sdk.util.VKUtil
import kotlinx.android.synthetic.main.activity_login.*
import ua.com.skywell.R

class LoginActivity: AppCompatActivity(),LoginView {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter=LoginPresenterImpl(this)
        initViews()
    }

    private fun initViews(){
        login_btn.setOnClickListener {presenter.onLoginBtnClick()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode,resultCode,data,presenter.getVkLoginCallback())
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(this,"Login successful, id: " + message,Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(message: String) {
        Toast.makeText(this,"Login failed, error:  " + message,Toast.LENGTH_SHORT).show()
    }
}
package ua.com.skywell.vk

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.vk.sdk.VKSdk
import com.vk.sdk.api.model.VKApiUserFull
import kotlinx.android.synthetic.main.vk_activity.*
import ua.com.skywell.R
import ua.com.skywell.databinding.VkActivityBinding


class VkActivity : AppCompatActivity(), VkView {

    lateinit var presenter: VkPresenter
    lateinit var binding: VkActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = VkPresenterImpl(baseContext, this)
        binding = DataBindingUtil.setContentView(this, R.layout.vk_activity)
        initViews()
    }

    private fun initViews() {
        presenter.loadUserInfo()
        presenter.loadWallPosts()
        vk_refresh.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun bindProfileInfo(profileInfo: VKApiUserFull) {
        binding.profile = profileInfo
    }

    override fun getWallRecycler() = vk_wall_recycler

    override fun setRefreshing(status: Boolean) {
        vk_refresh.isRefreshing = status
    }
}
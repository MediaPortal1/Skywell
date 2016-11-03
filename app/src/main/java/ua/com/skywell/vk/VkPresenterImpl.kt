package ua.com.skywell.vk

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vk.sdk.api.model.VKApiPost
import com.vk.sdk.api.model.VKApiUserFull
import ua.com.skywell.R
import kotlinx.android.synthetic.main.vk_activity.*
import java.util.*


class VkPresenterImpl(val context: Context, val view: VkView) : VkPresenter, VkPresenterCallback {

    private val model: VkModel = VkModelImpl.instance
    private var wallList = ArrayList<VKApiPost>()
    lateinit private var wallAdapter: VkWallAdapter

    private var isWallInit = false
    private var isRefreshing = false
    private var wallCount = 0
    private var isLoading = false


    override fun profileInfoLoaded(profileInfo: VKApiUserFull) {
        view.bindProfileInfo(profileInfo)
    }

    override fun initUserWall() {
        val recycler = view.getWallRecycler()
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        wallAdapter = VkWallAdapter(wallList, recycler, this)
        recycler.adapter = wallAdapter
        isWallInit = true
    }

    override fun loadUserInfo() {
        model.getUserInfo(this)
    }

    override fun onRefresh() {
        wallList.clear()
        wallCount = 0
        isRefreshing = true
        loadWallPosts(0)
    }


    override fun profileWallLoaded(wall: List<VKApiPost>) {
        wallList.addAll(wall)
        if (!isWallInit) {
            initUserWall()
        } else wallAdapter.notifyDataSetChanged()

        if (isRefreshing) {
            isRefreshing = false
            view.setRefreshing(false)
        }
        isLoading = false
    }

    override fun loadWallPosts(offset: Int) {
        isLoading = true
        model.getUserWall(this, offset)
    }

    override fun setItemCount(count: Int) {
        wallCount = count
    }

    override fun getItemCount() = wallCount

    override fun isLoading() = isLoading

    override fun setLoading(loading: Boolean) {
        isLoading = loading
    }
}
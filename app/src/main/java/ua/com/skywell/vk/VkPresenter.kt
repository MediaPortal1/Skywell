package ua.com.skywell.vk


/**
 * Created by Alex Poltavets on 02.11.2016.
 */
interface VkPresenter {
    fun loadUserInfo()
    fun initUserWall()
    fun onRefresh()
    fun loadWallPosts(offset: Int = 0)
    fun getItemCount(): Int
    fun isLoading(): Boolean
    fun setLoading(loading: Boolean)
}
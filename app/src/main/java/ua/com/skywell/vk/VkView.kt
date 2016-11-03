package ua.com.skywell.vk

import android.support.v7.widget.RecyclerView
import com.vk.sdk.api.model.VKApiUserFull

/**
 * Created by Alex Poltavets on 02.11.2016.
 */
interface VkView {
    fun bindProfileInfo(profileInfo: VKApiUserFull)
    fun getWallRecycler(): RecyclerView
    fun setRefreshing(status: Boolean)
}
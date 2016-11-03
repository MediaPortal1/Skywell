package ua.com.skywell.vk

import com.vk.sdk.api.model.VKApiPost
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKUsersArray


/**
 * Created by Alex Poltavets on 02.11.2016.
 */
interface VkPresenterCallback {
    fun profileInfoLoaded(profileInfo: VKApiUserFull)
    fun profileWallLoaded(wall: List<VKApiPost>)
    fun setItemCount(count: Int)

}
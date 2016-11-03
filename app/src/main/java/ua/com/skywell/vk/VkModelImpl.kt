package ua.com.skywell.vk

import android.util.Log
import com.vk.sdk.api.*
import com.vk.sdk.api.methods.VKApiWall
import com.vk.sdk.api.model.VKApiPost
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList
import com.vk.sdk.api.model.VKUsersArray
import org.json.JSONObject
import java.util.*

class VkModelImpl private constructor() : VkModel {

    companion object {
        val instance = VkModelImpl()
    }

    override fun getUserInfo(presenter: VkPresenterCallback) {
        val params = VKParameters()
        val request = VKRequest("users.get", VKParameters.from(VKApiConst.FIELDS, "photo_100,status,bdate,screen_name,home_town"), VKUsersArray::class.java)
        request.executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)
                val users = response?.parsedModel as VKUsersArray
                val user = users[0]
                Log.d("VKUSER", user.toString())
                presenter.profileInfoLoaded(user)
            }
        })
    }

    override fun getUserWall(callback: VkPresenterCallback, offset: Int) {
        val request = VKApi.wall().get(VKParameters.from(VKApiConst.COUNT, "10", VKApiConst.OFFSET, offset.toString()))
        request.executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)

                val responceJSON = response?.json?.optJSONObject("response")
                val wallItems = responceJSON?.getJSONArray("items")
                if (responceJSON != null) callback.setItemCount(responceJSON.getInt("count"))

                if (wallItems != null) {

                    var wallPost: JSONObject
                    var postList = ArrayList<VKApiPost>()

                    for (i in 0..wallItems.length() - 1) {
                        wallPost = wallItems.optJSONObject(i)
                        if (wallPost != null) {
                            postList.add(VKApiPost(wallPost))
                        }
                    }
                    callback.profileWallLoaded(postList)
                }
            }
        })

    }
}
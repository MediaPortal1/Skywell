package ua.com.skywell.vk

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vk.sdk.api.model.VKApiPost
import ua.com.skywell.R
import ua.com.skywell.databinding.ItemWallPostBinding


class VkWallAdapter(val postList: List<VKApiPost>, recyclerView: RecyclerView, val presenter: VkPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_LOADING = 0
    val VIEW_TYPE_POST = 1

    init {
        val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var previousTotal = 0
            private val visibleThreshold = 5
            internal var firstVisibleItem: Int = 0
            internal var visibleItemCount: Int = 0
            internal var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.childCount
                totalItemCount = linearLayoutManager.itemCount
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                val lastcountitem = linearLayoutManager.findLastVisibleItemPosition()

                if (postList.size == presenter.getItemCount()) {
                    presenter.setLoading(false)
                    return
                }
                if (presenter.isLoading()) {
                    if (totalItemCount >= previousTotal) {
                        previousTotal = totalItemCount
                    }
                }
                if (!presenter.isLoading() && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    presenter.loadWallPosts(postList.size)
                }
            }
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is VkHolderPost) holder.bindView.post = postList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            VIEW_TYPE_POST -> return VkHolderPost(DataBindingUtil.inflate<ItemWallPostBinding>(LayoutInflater.from(parent?.context), R.layout.item_wall_post, parent, false))
            VIEW_TYPE_LOADING -> return VkHolderLoading(LayoutInflater.from(parent?.context).inflate(R.layout.item_loading, parent, false))
            else -> return null
        }
    }

    override fun getItemCount() = if (presenter.isLoading()) postList.size + 1 else postList.size

    override fun getItemViewType(position: Int) = if (postList.size == position) VIEW_TYPE_LOADING else VIEW_TYPE_POST

    class VkHolderLoading(itemView: View) : RecyclerView.ViewHolder(itemView)

    class VkHolderPost(val bindView: ItemWallPostBinding) : RecyclerView.ViewHolder(bindView.root)
}

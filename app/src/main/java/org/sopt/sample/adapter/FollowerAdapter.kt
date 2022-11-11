package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.remote.ResponseGetFollowerListDTO
import org.sopt.sample.databinding.HeaderHomeFollowerBinding
import org.sopt.sample.databinding.ItemHomeFollowerBinding

class FollowerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList: List<ResponseGetFollowerListDTO.Follower> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> FollowerHeaderViewHolder(
                HeaderHomeFollowerBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            VIEW_TYPE_ITEM -> FollowerViewHolder(ItemHomeFollowerBinding.inflate(inflater, parent, false))
            else -> throw ClassCastException("Unkown View Type : ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowerViewHolder) holder.setFollower(followerList[position - 1])
    }

    override fun getItemCount(): Int {
        return followerList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return VIEW_TYPE_HEADER
        else return VIEW_TYPE_ITEM
    }

    fun setFollowerList(followerList: List<ResponseGetFollowerListDTO.Follower>) {
        this.followerList = followerList.toList()
        notifyDataSetChanged()
    }

    class FollowerViewHolder(private val binding: ItemHomeFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setFollower(follower: ResponseGetFollowerListDTO.Follower) {
            // avatar 링크 이미지로 변환하기
            binding.txtName.append("${follower.firstName} ${follower.lastName}")
            binding.txtEmail.append(follower.email)
        }
    }

    class FollowerHeaderViewHolder(private val binding: HeaderHomeFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }
}
package org.sopt.sample.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto.Follower
import org.sopt.sample.databinding.HeaderHomeFollowerBinding
import org.sopt.sample.databinding.ItemHomeFollowerBinding
import org.sopt.sample.util.DiffCallback

class FollowerAdapter(context: Context) : ListAdapter<Follower, RecyclerView.ViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> FollowerHeaderViewHolder(
                HeaderHomeFollowerBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            VIEW_TYPE_ITEM -> FollowerViewHolder(
                ItemHomeFollowerBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> throw ClassCastException("Unknown View Type : $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowerViewHolder) holder.setFollower(getItem(position - 1))
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER
        else VIEW_TYPE_ITEM
    }

    class FollowerViewHolder(private val binding: ItemHomeFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setFollower(follower: Follower) {
            binding.data = follower
        }
    }

    class FollowerHeaderViewHolder(private val binding: HeaderHomeFollowerBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = DiffCallback<Follower>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )

        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }
}

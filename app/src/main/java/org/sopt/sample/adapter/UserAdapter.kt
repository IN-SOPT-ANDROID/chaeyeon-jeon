package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.User
import org.sopt.sample.databinding.HeaderHomeUserBinding
import org.sopt.sample.databinding.ItemHomeUserBinding

class UserAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> UserHeaderViewHolder(
                HeaderHomeUserBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            VIEW_TYPE_ITEM -> UserViewHolder(ItemHomeUserBinding.inflate(inflater, parent, false))
            else -> throw ClassCastException("Unkown View Type : ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) holder.setUser(userList[position - 1])
    }

    override fun getItemCount(): Int {
        return userList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return VIEW_TYPE_HEADER
        else return VIEW_TYPE_ITEM
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: ItemHomeUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setUser(user: User) {
            binding.imgProfile.setImageResource(user.img)
            binding.txtName.append(user.id)
            binding.txtMbti.append(user.mbti)
        }
    }

    class UserHeaderViewHolder(private val binding: HeaderHomeUserBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }
}
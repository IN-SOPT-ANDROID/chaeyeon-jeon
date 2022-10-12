package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.User
import org.sopt.sample.databinding.ItemHomeUserBinding

class UserAdapter(context: Context): RecyclerView.Adapter<UserViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemHomeUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setUser(userList[position])
    }

    override fun getItemCount() = userList.size

    fun setUserList(userList: List<User>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }
}

class UserViewHolder(private val binding: ItemHomeUserBinding): RecyclerView.ViewHolder(binding.root) {
    fun setUser(user: User) {
        binding.imgProfile.setImageDrawable(binding.root.context.getDrawable(user.img))
        binding.txtName.append(user.id)
        binding.txtMbti.append(user.mbti)
    }
}
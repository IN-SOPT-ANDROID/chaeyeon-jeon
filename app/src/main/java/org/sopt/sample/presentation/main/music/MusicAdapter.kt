package org.sopt.sample.presentation.main.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.dto.response.ResponseMusicDto
import org.sopt.sample.databinding.HeaderMusicBinding
import org.sopt.sample.databinding.ItemMusicBinding
import org.sopt.sample.util.DiffCallback

class MusicAdapter(context: Context) :
    ListAdapter<ResponseMusicDto, RecyclerView.ViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> MusicHeaderViewHolder(
                HeaderMusicBinding.inflate(inflater, parent, false)
            )
            VIEW_TYPE_ITEM -> MusicViewHolder(
                ItemMusicBinding.inflate(inflater, parent, false)
            )
            else -> throw ClassCastException("Unknown View Type : $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MusicViewHolder) holder.setMusic(getItem(position - 1))
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER
        else VIEW_TYPE_ITEM
    }

    class MusicViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setMusic(music: ResponseMusicDto) {
            binding.data = music
        }
    }

    class MusicHeaderViewHolder(private val binding: HeaderMusicBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val diffUtil = DiffCallback<ResponseMusicDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )

        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }
}

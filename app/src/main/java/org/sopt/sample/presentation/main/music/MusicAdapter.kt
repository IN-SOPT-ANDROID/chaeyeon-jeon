package org.sopt.sample.presentation.main.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto.Music
import org.sopt.sample.databinding.ItemMusicBinding

class MusicAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var musicList: List<Music> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MusicViewHolder(ItemMusicBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MusicViewHolder) holder.setMusic(musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun setMusicList(musicList: List<Music>) {
        this.musicList = musicList
        notifyDataSetChanged()
    }

    class MusicViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setMusic(music: Music) {
            binding.data = music
        }
    }
}

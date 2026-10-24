package com.climatechampion.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.ItemVideoBinding
import com.climatechampion.model.Video

/**
 * RecyclerView adapter for the video list screen (grid or horizontal strip).
 */
class VideoAdapter(
    private val onVideoClick: (Video) -> Unit
) : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    private val watchedVideoIds = mutableSetOf<String>()

    fun markWatched(videoId: String) {
        watchedVideoIds.add(videoId)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position), watchedVideoIds.contains(getItem(position).videoId))
    }

    inner class VideoViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video, isWatched: Boolean) {
            binding.tvTitle.text = video.title
            binding.tvDuration.text = video.formattedDuration()
            binding.tvPoints.text = binding.root.context.getString(
                R.string.points_reward_format, video.pointsReward
            )

            // Watched indicator
            binding.ivWatchedBadge.visibility =
                if (isWatched) android.view.View.VISIBLE else android.view.View.GONE

            // Warning for short videos (should be >= 5 min per business rule)
            binding.tvDurationWarning.visibility =
                if (!video.isMinimumLength()) android.view.View.VISIBLE else android.view.View.GONE

            Glide.with(binding.root.context)
                .load(video.thumbnailUrl)
                .placeholder(R.drawable.ic_video_placeholder)
                .centerCrop()
                .into(binding.ivThumbnail)

            binding.root.setOnClickListener { onVideoClick(video) }
        }
    }

    class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(old: Video, new: Video) = old.videoId == new.videoId
        override fun areContentsTheSame(old: Video, new: Video) = old == new
    }
}

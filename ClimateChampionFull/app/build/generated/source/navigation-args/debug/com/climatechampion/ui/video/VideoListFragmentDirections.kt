package com.climatechampion.ui.video

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class VideoListFragmentDirections private constructor() {
  private data class ActionVideoListFragmentToVideoPlayerFragment(
    public val videoId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_videoListFragment_to_videoPlayerFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("videoId", this.videoId)
        return result
      }
  }

  public companion object {
    public fun actionVideoListFragmentToVideoPlayerFragment(videoId: String): NavDirections =
        ActionVideoListFragmentToVideoPlayerFragment(videoId)
  }
}

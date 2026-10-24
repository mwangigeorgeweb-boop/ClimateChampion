package com.climatechampion.ui.video

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class VideoPlayerFragmentDirections private constructor() {
  private data class ActionVideoPlayerFragmentSelf(
    public val videoId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_videoPlayerFragment_self

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("videoId", this.videoId)
        return result
      }
  }

  public companion object {
    public fun actionVideoPlayerFragmentSelf(videoId: String): NavDirections =
        ActionVideoPlayerFragmentSelf(videoId)
  }
}

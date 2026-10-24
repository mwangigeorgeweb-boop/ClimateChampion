package com.climatechampion.ui.topics

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class TopicDetailFragmentDirections private constructor() {
  private data class ActionTopicDetailFragmentToQuizFragment(
    public val topicId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_topicDetailFragment_to_quizFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("topicId", this.topicId)
        return result
      }
  }

  private data class ActionTopicDetailFragmentToVideoPlayerFragment(
    public val videoId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_topicDetailFragment_to_videoPlayerFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("videoId", this.videoId)
        return result
      }
  }

  public companion object {
    public fun actionTopicDetailFragmentToQuizFragment(topicId: String): NavDirections =
        ActionTopicDetailFragmentToQuizFragment(topicId)

    public fun actionTopicDetailFragmentToVideoPlayerFragment(videoId: String): NavDirections =
        ActionTopicDetailFragmentToVideoPlayerFragment(videoId)
  }
}

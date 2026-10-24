package com.climatechampion.ui.topics

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class TopicListFragmentDirections private constructor() {
  private data class ActionTopicListFragmentToTopicDetailFragment(
    public val topicId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_topicListFragment_to_topicDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("topicId", this.topicId)
        return result
      }
  }

  public companion object {
    public fun actionTopicListFragmentToTopicDetailFragment(topicId: String): NavDirections =
        ActionTopicListFragmentToTopicDetailFragment(topicId)
  }
}

package com.climatechampion.ui.home

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToTopicDetailFragment(
    public val topicId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_topicDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("topicId", this.topicId)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToTopicDetailFragment(topicId: String): NavDirections =
        ActionHomeFragmentToTopicDetailFragment(topicId)

    public fun actionHomeFragmentToCarbonCalculatorFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_carbonCalculatorFragment)
  }
}

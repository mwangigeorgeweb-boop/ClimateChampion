package com.climatechampion.ui.quiz

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Int
import kotlin.String

public class QuizResultFragmentDirections private constructor() {
  private data class ActionQuizResultFragmentToQuizFragment(
    public val topicId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_quizResultFragment_to_quizFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("topicId", this.topicId)
        return result
      }
  }

  public companion object {
    public fun actionQuizResultFragmentToQuizFragment(topicId: String): NavDirections =
        ActionQuizResultFragmentToQuizFragment(topicId)
  }
}

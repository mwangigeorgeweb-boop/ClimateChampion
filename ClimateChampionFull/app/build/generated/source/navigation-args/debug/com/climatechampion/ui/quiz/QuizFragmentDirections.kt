package com.climatechampion.ui.quiz

import android.os.Bundle
import androidx.navigation.NavDirections
import com.climatechampion.R
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

public class QuizFragmentDirections private constructor() {
  private data class ActionQuizFragmentToQuizResultFragment(
    public val score: Int,
    public val totalQuestions: Int,
    public val percentage: Float,
    public val passed: Boolean,
    public val pointsEarned: Int,
    public val topicId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_quizFragment_to_quizResultFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("score", this.score)
        result.putInt("totalQuestions", this.totalQuestions)
        result.putFloat("percentage", this.percentage)
        result.putBoolean("passed", this.passed)
        result.putInt("pointsEarned", this.pointsEarned)
        result.putString("topicId", this.topicId)
        return result
      }
  }

  public companion object {
    public fun actionQuizFragmentToQuizResultFragment(
      score: Int,
      totalQuestions: Int,
      percentage: Float,
      passed: Boolean,
      pointsEarned: Int,
      topicId: String,
    ): NavDirections = ActionQuizFragmentToQuizResultFragment(score, totalQuestions, percentage,
        passed, pointsEarned, topicId)
  }
}

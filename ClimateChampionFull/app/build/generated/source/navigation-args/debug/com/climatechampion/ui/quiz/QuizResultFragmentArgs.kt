package com.climatechampion.ui.quiz

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class QuizResultFragmentArgs(
  public val score: Int,
  public val totalQuestions: Int,
  public val percentage: Float,
  public val passed: Boolean,
  public val pointsEarned: Int,
  public val topicId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("score", this.score)
    result.putInt("totalQuestions", this.totalQuestions)
    result.putFloat("percentage", this.percentage)
    result.putBoolean("passed", this.passed)
    result.putInt("pointsEarned", this.pointsEarned)
    result.putString("topicId", this.topicId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("score", this.score)
    result.set("totalQuestions", this.totalQuestions)
    result.set("percentage", this.percentage)
    result.set("passed", this.passed)
    result.set("pointsEarned", this.pointsEarned)
    result.set("topicId", this.topicId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): QuizResultFragmentArgs {
      bundle.setClassLoader(QuizResultFragmentArgs::class.java.classLoader)
      val __score : Int
      if (bundle.containsKey("score")) {
        __score = bundle.getInt("score")
      } else {
        throw IllegalArgumentException("Required argument \"score\" is missing and does not have an android:defaultValue")
      }
      val __totalQuestions : Int
      if (bundle.containsKey("totalQuestions")) {
        __totalQuestions = bundle.getInt("totalQuestions")
      } else {
        throw IllegalArgumentException("Required argument \"totalQuestions\" is missing and does not have an android:defaultValue")
      }
      val __percentage : Float
      if (bundle.containsKey("percentage")) {
        __percentage = bundle.getFloat("percentage")
      } else {
        throw IllegalArgumentException("Required argument \"percentage\" is missing and does not have an android:defaultValue")
      }
      val __passed : Boolean
      if (bundle.containsKey("passed")) {
        __passed = bundle.getBoolean("passed")
      } else {
        throw IllegalArgumentException("Required argument \"passed\" is missing and does not have an android:defaultValue")
      }
      val __pointsEarned : Int
      if (bundle.containsKey("pointsEarned")) {
        __pointsEarned = bundle.getInt("pointsEarned")
      } else {
        throw IllegalArgumentException("Required argument \"pointsEarned\" is missing and does not have an android:defaultValue")
      }
      val __topicId : String?
      if (bundle.containsKey("topicId")) {
        __topicId = bundle.getString("topicId")
        if (__topicId == null) {
          throw IllegalArgumentException("Argument \"topicId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"topicId\" is missing and does not have an android:defaultValue")
      }
      return QuizResultFragmentArgs(__score, __totalQuestions, __percentage, __passed,
          __pointsEarned, __topicId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): QuizResultFragmentArgs {
      val __score : Int?
      if (savedStateHandle.contains("score")) {
        __score = savedStateHandle["score"]
        if (__score == null) {
          throw IllegalArgumentException("Argument \"score\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"score\" is missing and does not have an android:defaultValue")
      }
      val __totalQuestions : Int?
      if (savedStateHandle.contains("totalQuestions")) {
        __totalQuestions = savedStateHandle["totalQuestions"]
        if (__totalQuestions == null) {
          throw IllegalArgumentException("Argument \"totalQuestions\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"totalQuestions\" is missing and does not have an android:defaultValue")
      }
      val __percentage : Float?
      if (savedStateHandle.contains("percentage")) {
        __percentage = savedStateHandle["percentage"]
        if (__percentage == null) {
          throw IllegalArgumentException("Argument \"percentage\" of type float does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"percentage\" is missing and does not have an android:defaultValue")
      }
      val __passed : Boolean?
      if (savedStateHandle.contains("passed")) {
        __passed = savedStateHandle["passed"]
        if (__passed == null) {
          throw IllegalArgumentException("Argument \"passed\" of type boolean does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"passed\" is missing and does not have an android:defaultValue")
      }
      val __pointsEarned : Int?
      if (savedStateHandle.contains("pointsEarned")) {
        __pointsEarned = savedStateHandle["pointsEarned"]
        if (__pointsEarned == null) {
          throw IllegalArgumentException("Argument \"pointsEarned\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"pointsEarned\" is missing and does not have an android:defaultValue")
      }
      val __topicId : String?
      if (savedStateHandle.contains("topicId")) {
        __topicId = savedStateHandle["topicId"]
        if (__topicId == null) {
          throw IllegalArgumentException("Argument \"topicId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"topicId\" is missing and does not have an android:defaultValue")
      }
      return QuizResultFragmentArgs(__score, __totalQuestions, __percentage, __passed,
          __pointsEarned, __topicId)
    }
  }
}

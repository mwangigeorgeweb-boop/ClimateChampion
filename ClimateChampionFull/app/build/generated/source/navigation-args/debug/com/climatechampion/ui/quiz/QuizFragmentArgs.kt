package com.climatechampion.ui.quiz

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class QuizFragmentArgs(
  public val topicId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("topicId", this.topicId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("topicId", this.topicId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): QuizFragmentArgs {
      bundle.setClassLoader(QuizFragmentArgs::class.java.classLoader)
      val __topicId : String?
      if (bundle.containsKey("topicId")) {
        __topicId = bundle.getString("topicId")
        if (__topicId == null) {
          throw IllegalArgumentException("Argument \"topicId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"topicId\" is missing and does not have an android:defaultValue")
      }
      return QuizFragmentArgs(__topicId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): QuizFragmentArgs {
      val __topicId : String?
      if (savedStateHandle.contains("topicId")) {
        __topicId = savedStateHandle["topicId"]
        if (__topicId == null) {
          throw IllegalArgumentException("Argument \"topicId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"topicId\" is missing and does not have an android:defaultValue")
      }
      return QuizFragmentArgs(__topicId)
    }
  }
}

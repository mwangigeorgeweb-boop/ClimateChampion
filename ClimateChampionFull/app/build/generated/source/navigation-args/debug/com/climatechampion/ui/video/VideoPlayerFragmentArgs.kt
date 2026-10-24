package com.climatechampion.ui.video

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class VideoPlayerFragmentArgs(
  public val videoId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("videoId", this.videoId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("videoId", this.videoId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): VideoPlayerFragmentArgs {
      bundle.setClassLoader(VideoPlayerFragmentArgs::class.java.classLoader)
      val __videoId : String?
      if (bundle.containsKey("videoId")) {
        __videoId = bundle.getString("videoId")
        if (__videoId == null) {
          throw IllegalArgumentException("Argument \"videoId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"videoId\" is missing and does not have an android:defaultValue")
      }
      return VideoPlayerFragmentArgs(__videoId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): VideoPlayerFragmentArgs {
      val __videoId : String?
      if (savedStateHandle.contains("videoId")) {
        __videoId = savedStateHandle["videoId"]
        if (__videoId == null) {
          throw IllegalArgumentException("Argument \"videoId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"videoId\" is missing and does not have an android:defaultValue")
      }
      return VideoPlayerFragmentArgs(__videoId)
    }
  }
}

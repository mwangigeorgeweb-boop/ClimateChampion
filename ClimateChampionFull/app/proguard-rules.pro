# Preserve Firebase Data Models from obfuscation
-keepclassmembers class com.climatechampion.model.** { *; }
-keep class com.climatechampion.model.** { *; }

# Media3 / ExoPlayer
-keep class androidx.media3.** { *; }

# ViewBinding
-keep class com.climatechampion.databinding.** { *; }
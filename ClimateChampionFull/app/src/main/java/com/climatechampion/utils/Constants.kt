package com.climatechampion.utils

/**
 * App-wide constants for Climate Champion.
 */
object Constants {

    // Firebase Firestore collection names
    const val COLLECTION_USERS = "users"
    const val COLLECTION_TOPICS = "topics"
    const val COLLECTION_VIDEOS = "videos"
    const val COLLECTION_QUESTIONS = "questions"
    const val COLLECTION_LEADERBOARD = "leaderboard"
    const val SUBCOLLECTION_PROGRESS = "progress"
    const val SUBCOLLECTION_QUIZ_RESULTS = "quizResults"
    const val SUBCOLLECTION_SUBTOPICS = "subtopics"

    // Points system
    const val POINTS_TOPIC_COMPLETE = 50
    const val POINTS_VIDEO_WATCHED = 30
    const val POINTS_QUIZ_PASS = 100
    const val POINTS_QUIZ_PERFECT = 200
    const val POINTS_DAILY_LOGIN = 10

    // Level thresholds
    const val LEVEL_INTERMEDIATE_THRESHOLD = 2000
    const val LEVEL_EXPERT_THRESHOLD = 5000

    // Level names
    const val LEVEL_BEGINNER = "Beginner"
    const val LEVEL_INTERMEDIATE = "Intermediate"
    const val LEVEL_EXPERT = "Expert"

    // Quiz settings
    const val QUIZ_PASS_PERCENTAGE = 70f
    const val QUIZ_TIMER_SECONDS = 30L
    const val LEADERBOARD_LIMIT = 50L

    // Minimum video duration (5 minutes in seconds)
    const val MIN_VIDEO_DURATION_SECONDS = 300

    // Navigation argument keys
    const val ARG_TOPIC_ID = "topicId"
    const val ARG_VIDEO_ID = "videoId"
    const val ARG_QUIZ_RESULT = "quizResult"

    // Shared preferences keys
    const val PREFS_NAME = "climate_champion_prefs"
    const val PREF_LAST_LOGIN_DATE = "last_login_date"
    const val PREF_DAILY_POINTS_AWARDED = "daily_points_awarded"

    // Google Sign-In request code
    const val RC_SIGN_IN = 9001
}

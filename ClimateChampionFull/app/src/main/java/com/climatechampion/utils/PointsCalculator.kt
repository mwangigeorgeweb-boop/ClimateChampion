package com.climatechampion.utils

/**
 * Utility object for calculating points earned for various actions.
 */
object PointsCalculator {

    /**
     * Calculates points earned after a quiz submission.
     * - Pass (>=70%): 100 points
     * - Perfect (100%): 200 points
     * - Fail: 0 points
     */
    fun calculateQuizPoints(percentage: Float): Int = when {
        percentage >= 100f -> Constants.POINTS_QUIZ_PERFECT
        percentage >= Constants.QUIZ_PASS_PERCENTAGE -> Constants.POINTS_QUIZ_PASS
        else -> 0
    }

    /**
     * Returns the level name for a given total points value.
     */
    fun getLevel(totalPoints: Int): String = when {
        totalPoints >= Constants.LEVEL_EXPERT_THRESHOLD -> Constants.LEVEL_EXPERT
        totalPoints >= Constants.LEVEL_INTERMEDIATE_THRESHOLD -> Constants.LEVEL_INTERMEDIATE
        else -> Constants.LEVEL_BEGINNER
    }

    /**
     * Returns the points needed to reach the next level from current total.
     * Returns 0 if already at Expert.
     */
    fun pointsToNextLevel(currentPoints: Int): Int = when {
        currentPoints >= Constants.LEVEL_EXPERT_THRESHOLD -> 0
        currentPoints >= Constants.LEVEL_INTERMEDIATE_THRESHOLD ->
            Constants.LEVEL_EXPERT_THRESHOLD - currentPoints
        else -> Constants.LEVEL_INTERMEDIATE_THRESHOLD - currentPoints
    }

    /**
     * Returns progress percentage toward the next level (0-100).
     */
    fun progressToNextLevel(currentPoints: Int): Int = when {
        currentPoints >= Constants.LEVEL_EXPERT_THRESHOLD -> 100
        currentPoints >= Constants.LEVEL_INTERMEDIATE_THRESHOLD -> {
            val range = Constants.LEVEL_EXPERT_THRESHOLD - Constants.LEVEL_INTERMEDIATE_THRESHOLD
            val progress = currentPoints - Constants.LEVEL_INTERMEDIATE_THRESHOLD
            ((progress.toFloat() / range.toFloat()) * 100).toInt()
        }
        else -> {
            val range = Constants.LEVEL_INTERMEDIATE_THRESHOLD
            ((currentPoints.toFloat() / range.toFloat()) * 100).toInt()
        }
    }

    /**
     * Returns the emoji badge for a given level string.
     */
    fun levelEmoji(level: String): String = when (level) {
        Constants.LEVEL_EXPERT -> "🏆"
        Constants.LEVEL_INTERMEDIATE -> "⭐"
        else -> "🌱"
    }

    /**
     * Returns the rank medal emoji for leaderboard positions 1-3.
     */
    fun rankMedal(rank: Int): String = when (rank) {
        1 -> "🥇"
        2 -> "🥈"
        3 -> "🥉"
        else -> "#$rank"
    }
}

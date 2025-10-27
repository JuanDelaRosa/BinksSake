package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: String,
    val userId: String,
    val userName: String,
    val userPhotoUrl: String? = null,
    val content: String,
    val media: List<Media> = emptyList(),
    val taggedFigures: List<FigureTag> = emptyList(),
    val isNSFW: Boolean = false,
    val likes: Int = 0,
    val commentsCount: Int = 0,
    val sharesCount: Int = 0,
    val isLikedByCurrentUser: Boolean = false,
    val visibility: PostVisibility = PostVisibility.PUBLIC,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class Media(
    val id: String,
    val url: String,
    val type: MediaType,
    val thumbnailUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val duration: Int? = null
)

@Serializable
enum class MediaType {
    IMAGE,
    VIDEO,
    GIF
}

@Serializable
data class FigureTag(
    val figureId: String,
    val figureName: String,
    val figureImage: String
)

@Serializable
enum class PostVisibility {
    PUBLIC,
    FOLLOWERS_ONLY,
    PRIVATE
}

@Serializable
data class Comment(
    val id: String,
    val postId: String,
    val userId: String,
    val userName: String,
    val userPhotoUrl: String? = null,
    val content: String,
    val parentCommentId: String? = null,
    val likes: Int = 0,
    val isLikedByCurrentUser: Boolean = false,
    val repliesCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class Like(
    val id: String,
    val userId: String,
    val targetType: LikeTargetType,
    val targetId: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
enum class LikeTargetType {
    POST,
    COMMENT
}

@Serializable
data class Follow(
    val id: String,
    val followerId: String,
    val followingId: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
data class UserFollowInfo(
    val userId: String,
    val displayName: String,
    val username: String,
    val photoUrl: String? = null,
    val bio: String? = null,
    val isVerified: Boolean = false,
    val isFollowedByCurrentUser: Boolean = false,
    val followsCurrentUser: Boolean = false,
    val totalFigures: Int = 0,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)

@Serializable
data class FeedItem(
    val id: String,
    val type: FeedItemType,
    val post: Post? = null,
    val achievement: AchievementFeedItem? = null,
    val newFigure: NewFigureFeedItem? = null,
    val timestamp: Long
)

@Serializable
enum class FeedItemType {
    POST,
    ACHIEVEMENT_UNLOCK,
    NEW_FIGURE_ADDED,
    FIGURE_UPLOAD,
    SERIES_COMPLETED
}

@Serializable
data class AchievementFeedItem(
    val userId: String,
    val userName: String,
    val userPhotoUrl: String? = null,
    val achievement: Achievement,
    val timestamp: Long
)

@Serializable
data class NewFigureFeedItem(
    val figure: Figure,
    val uploadedBy: String,
    val uploaderName: String,
    val uploaderPhotoUrl: String? = null,
    val timestamp: Long
)

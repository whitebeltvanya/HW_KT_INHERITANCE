package ru.netology.post


data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String,
    val isFavorite: Boolean = false,
    val canEdit: Boolean = false,
    val canDelete: Boolean = false,
    val views: Views? = null,
    val likes: Likes? = null,
    val reposts: Reposts? = null,
    var attachments: Set<Attachment> = mutableSetOf()
)

data class Views(val counts: Int)
data class Likes(
    val counts: Int, val userLikes: Boolean = false,
    val canLike: Boolean = false, val canPublish: Boolean = false
)

data class Reposts(val count: Integer, val userReposted: Boolean = false)

enum class AttachmentType {
    FOTO,
    AUDIO,
    VIDEO,
    FILE,
    STICKER
}

interface Attachment {
    val type: AttachmentType
}

data class FotoAttachment(
    val foto: Foto
) : Attachment {
    override val type: AttachmentType = AttachmentType.FOTO
}

data class Foto(
    val id: Int = 0,
    val ownerId: Int = 0,
    val albumId: Int = 0,
    val userId: Int = 0,
    val width: Int = 0,
    val height: Int = 0
)

data class AudioAttachment(
    val audio: Audio
) : Attachment {
    override val type: AttachmentType = AttachmentType.AUDIO
}

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url: String = ""
)

data class VideoAttachment(
    val video: Video
) : Attachment {
    override val type: AttachmentType = AttachmentType.VIDEO
}

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
)

data class FileAttachment(
    val file: File
) : Attachment {
    override val type: AttachmentType = AttachmentType.FILE
}

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0,
    val ext: String = "",
    val url: String = ""
)

data class StickerAttachment(
    val sticker: Sticker
) : Attachment {
    override val type: AttachmentType = AttachmentType.STICKER
}

data class Sticker(
    val productId: Int = 0,
    val stickerId: Int = 0,
    val animationUrl: String = "",
    val isAllowed: Boolean = true
)

object WallService {

    private var newId = 0
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = getNewId())
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, postChecked) in posts.withIndex()) {
            if (postChecked.id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun addAttachment(post: Post, attachment: Attachment): Boolean {
        for ((index, postChecked) in posts.withIndex()) {
            if (postChecked.id == post.id) {
                post.attachments += attachment
                return true
            }
        }
        return false
    }

    fun getNewId(): Int = ++newId //thanks to jomarzka
    fun getPostsCounts() = posts.size
    fun clear() {
        posts = emptyArray<Post>()
        newId = 0
    }
}

fun main() {
    val post = Post(text = "Blah-blah-blah")
    val wallService = WallService

    println(wallService.add(post))
    println("All posts counts: ${wallService.getPostsCounts()}")

    val newVideoAttach = VideoAttachment(Video(title = "New video"))
    wallService.addAttachment(post, newVideoAttach)

    val newPost = Post(id = 3, text = "New Text")
    if (wallService.update(newPost)) {
        println(newPost)
    } else {
        println("Post id=${newPost.id} not found")
    }


}
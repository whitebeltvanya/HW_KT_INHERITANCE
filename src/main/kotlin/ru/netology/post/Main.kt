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
    val views: Views = Views(0),
    val likes: Likes = Likes(0)
)

data class Views(val counts: Int)
data class Likes(
    val counts: Int, val userLikes: Boolean = false,
    val canLike: Boolean = false, val canPublish: Boolean = false
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

    fun getNewId(): Int = newId++
    fun getPostsCounts() = posts.size
}

fun main() {
    val post = Post(text = "Blah-blah-blah")
    val wallService = WallService

    println(wallService.add(post))
    println(wallService.add(post))
    println(wallService.getPostsCounts())

    val newPost = Post(id = 3, text = "New Text")
    if (wallService.update(newPost)) {
        println(newPost)
    } else {
        println("Post not found")
    }


}
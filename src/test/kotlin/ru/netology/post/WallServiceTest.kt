package ru.netology.post

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {
        val post = Post(text = "test")
        val expected = 1
        val actual = WallService.add(post).id
        assertEquals(expected, actual)
    }


    @Test
    fun updateTestFall() {
        val newPost = Post(text = "New post")
        val postUpdated = Post(id = 3, text = "Post with updated text")
        WallService.add(newPost)
        assertFalse(WallService.update(postUpdated))
    }

    @Test
    fun updateTestRight() {
        val newPost = Post(text = "New post")
        val postUpdated = Post(id = 1, text = "Post with updated text")
        WallService.add(newPost)
        assertTrue(WallService.update(postUpdated))
    }

}
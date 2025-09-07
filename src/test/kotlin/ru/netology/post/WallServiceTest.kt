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
        WallService.add(post)
        WallService.add(post)
        val expected = 2
        val actual = WallService.add(post).id
        assertEquals(expected, actual)
    }


    @Test
    fun updateTestFall() {
        val newPost = Post(id = 3, text = "test 2")
        WallService.update(newPost)
        val expected = false
        val actual = WallService.update(newPost)
        assertEquals(expected, actual)
    }

    @Test
    fun updateTestRight() {
        val post = Post(text = "test upd 1")
        val postCheck = Post(id=0, text = "")
        WallService.add(post)

        val expected = true
        val actual = WallService.update(postCheck)
        assertEquals(expected, actual)
    }

}
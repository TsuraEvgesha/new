package ru.netology.coroutines.dto

class PostWithCommentAvthor (
    val post: Post,
    val comments: List<Comment>,
    val author: Author,
    val authorComment:List<AuthorComment>,
    )
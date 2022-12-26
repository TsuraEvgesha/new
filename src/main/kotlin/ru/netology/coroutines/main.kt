package ru.netology.coroutines

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import ru.netology.coroutines.dto.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


//private val gson = Gson()
//private val BASE_URL = "http://127.0.0.1:9999"
//private val client = OkHttpClient.Builder()
//    .addInterceptor(HttpLoggingInterceptor(::println).apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    })
//    .connectTimeout(30, TimeUnit.SECONDS)
//    .build()
//
//fun main() {
//    with(CoroutineScope(EmptyCoroutineContext)) {
//        launch {
//            try {
//                val posts = getPosts(client)
//                    .map { post ->
//                        val authorComment = getComments(client,post.id)
//                            .map {
//                                AuthorComment(author = getAuthor(client,it.authorId))
//                            }
//                        async {
//                            PostWithCommentAvthor(post, getComments(client, post.id), getAuthor(client,post.authorId),
//                                authorComment
//                            )
//                        }
//                    }.awaitAll()
//                println(posts)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//    Thread.sleep(30_000L)
//}
//
//suspend fun OkHttpClient.apiCall(url: String): Response {
//    return suspendCoroutine { continuation ->
//        Request.Builder()
//            .url(url)
//            .build()
//            .let(::newCall)
//            .enqueue(object : Callback {
//                override fun onResponse(call: Call, response: Response) {
//                    continuation.resume(response)
//                }
//
//                override fun onFailure(call: Call, e: IOException) {
//                    continuation.resumeWithException(e)
//                }
//            })
//    }
//}
//
//suspend fun <T> makeRequest(url: String, client: OkHttpClient, typeToken: TypeToken<T>): T =
//    withContext(Dispatchers.IO) {
//        client.apiCall(url)
//            .let { response ->
//                if (!response.isSuccessful) {
//                    response.close()
//                    throw RuntimeException(response.message)
//                }
//                val body = response.body ?: throw RuntimeException("response body is null")
//                gson.fromJson(body.string(), typeToken.type)
//            }
//    }
//
//suspend fun getPosts(client: OkHttpClient): List<Post> =
//    makeRequest("$BASE_URL/api/slow/posts", client, object : TypeToken<List<Post>>() {})
//
//suspend fun getComments(client: OkHttpClient, id: Long): List<Comment> =
//    makeRequest("$BASE_URL/api/slow/posts/$id/comments", client, object : TypeToken<List<Comment>>() {})
//
//suspend fun getAuthor(client: OkHttpClient,id: Long):Author =
//    makeRequest("$BASE_URL/api/slow/authors/$id", client, object : TypeToken<Author>() {})
//
//
//
//
//fun main() = runBlocking {
//    val job = CoroutineScope(EmptyCoroutineContext).launch {
//        launch {
//            delay(500)
//            println("ok") // <--
//        }
//        launch {
//            delay(500)
//            println("ok")
//        }
//    }
//    delay(100)
//    job.cancelAndJoin()
//}
//fun main() = runBlocking {
//    val job = CoroutineScope(EmptyCoroutineContext).launch {
//        val child = launch {
//            delay(500)
//            println("ok") // <--
//        }
//        launch {
//            delay(500)
//            println("1")
//        }
//        delay(100)
//        child.cancel()
//    }
//    delay(100)
//    job.join()
//}
//fun main() {
//    with(CoroutineScope(EmptyCoroutineContext)) {
//        try {
//            launch {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            println("2")
//            e.printStackTrace() // <--
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                launch {
//                    delay(500)
//                    throw Exception("something bad happened") // <--
//                }
//                launch {
//                    throw Exception("1")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                launch {
//                    delay(500)
//                    throw Exception("something bad happened") // <--
//                }
//                launch {
//                    throw Exception("something bad happened")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        CoroutineScope(EmptyCoroutineContext).launch {
//            launch {
//                delay(1000)
//                println("ok") // <--
//            }
//            launch {
//                delay(500)
//                println("ok")
//            }
//            throw Exception("something bad happened")
//        }
//    }
//    Thread.sleep(1000)
//}
fun main() {
    CoroutineScope(EmptyCoroutineContext).launch {
        CoroutineScope(EmptyCoroutineContext + SupervisorJob()).launch {
            launch {
                delay(1000)
                println("ok") // <--
            }
            launch {
                delay(500)
                println("ok")
            }
            throw Exception("something bad happened")
        }
    }
    Thread.sleep(1000)
}
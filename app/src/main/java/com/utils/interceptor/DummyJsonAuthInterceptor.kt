package com.utils.interceptor

import com.utils.sharedPrefs.AppPrefs
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class DummyJsonAuthInterceptor @Inject constructor(
    private val prefs: AppPrefs
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation
            .method()
            .annotations
            .any { it.annotationClass == Authentication::class }

        return if (shouldAttachAuthHeader) {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${prefs.token}")
                    .build()
            )
        } else chain.proceed(chain.request())
    }

}

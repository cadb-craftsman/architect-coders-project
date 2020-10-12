package com.woowrale.openlibrary.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.mockwebserver.MockWebServer

@Module
class FakeWebServerModule {

    @Singleton
    @Provides
    @Named("baseUrl")
    fun baseUrlProvider() = "http://127.0.0.1:8080"


    @Provides
    @Singleton
    fun mockWebServerProvider(): MockWebServer {
        var mockWebServer: MockWebServer? = null
        val thread = Thread(Runnable {
            mockWebServer = MockWebServer()
            mockWebServer?.start(8080)
        })
        thread.start()
        thread.join()
        return mockWebServer ?: throw NullPointerException()
    }

}
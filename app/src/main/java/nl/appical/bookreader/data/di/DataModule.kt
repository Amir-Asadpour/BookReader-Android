package nl.appical.bookreader.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import nl.appical.bookreader.data.network.RemoteService
import nl.appical.bookreader.data.repositories.BooksRepositoryImpl
import nl.appical.bookreader.domain.repositories.BooksRepository
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://b79940d4-eac9-46eb-9f41-059e38e16a3f.mock.pstmn.io/")
        .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)

    @Singleton
    @Provides
    fun provideBooksRepository(remoteService: RemoteService): BooksRepository =
        BooksRepositoryImpl(remoteService)
}
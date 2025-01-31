package searchtickets.app.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import searchtickets.app.data.api.ApiConst.BASE_URL
import searchtickets.app.data.mappers.ApiAllToAllMapper
import searchtickets.app.data.mappers.ApiFlightsToFlightsMapper
import searchtickets.app.data.mappers.ApiInfoToInfoMapper
import searchtickets.app.data.repositories.FlightsRepositoryImpl
import searchtickets.app.data.repositories.ImagesRepositoryImpl
import searchtickets.app.data.repositories.ShowAllRepositoryImpl
import searchtickets.app.domain.repositories.FlightsRepository
import searchtickets.app.domain.repositories.ImagesRepository
import searchtickets.app.domain.repositories.ShowAllRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun okHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(
                10000,
                TimeUnit.SECONDS
            ) // Увеличение времени ожидания для установления соединения
            .readTimeout(3000, TimeUnit.SECONDS) // Увеличение времени ожидания для чтения данных
            .writeTimeout(3000, TimeUnit.SECONDS) // Увеличение времени ожидания для записи данных
            .retryOnConnectionFailure(true) // Включение повторных попыток при сбоях соединения
            .build()
        return client
    }

    @Singleton
    @Provides
    fun provideHotelsApi(okHttpClient: OkHttpClient, moshi: Moshi): ImagesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ImagesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFlightsApi(okHttpClient: OkHttpClient, moshi: Moshi): FlightsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FlightsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAllApi(okHttpClient: OkHttpClient, moshi: Moshi): ShowAllApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ShowAllApi::class.java)
    }

    @Singleton
    @Provides
    fun providesHotelsRepository(
        imagesApi: ImagesApi,
        hotelsMapper: ApiInfoToInfoMapper
    ): ImagesRepository {
        return ImagesRepositoryImpl(imagesApi, hotelsMapper)
    }

    @Singleton
    @Provides
    fun providesFlightsRepository(
        flightsApi: FlightsApi,
        flightsMapper: ApiFlightsToFlightsMapper
    ): FlightsRepository {
        return FlightsRepositoryImpl(flightsApi, flightsMapper)
    }

    @Singleton
    @Provides
    fun providesShowAllRepository(
        showAllApi: ShowAllApi,
        mapper: ApiAllToAllMapper
    ): ShowAllRepository {
        return ShowAllRepositoryImpl(showAllApi, mapper)
    }
}

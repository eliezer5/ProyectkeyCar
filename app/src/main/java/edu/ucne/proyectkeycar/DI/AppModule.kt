package edu.ucne.proyectkeycar.DI

import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.Module
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.proyectkeycar.data.database.KeyCarDb
import edu.ucne.proyectkeycar.data.remote.KeyAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val BASE_URL = "https://keycarap2-chhkgpada5g3bda5.canadacentral-01.azurewebsites.net/"
    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    @Provides
    @Singleton
    fun  provideKeyCarApi(moshi: Moshi): KeyAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(KeyAPI::class.java)
    }

    @Provides
    @Singleton
    fun providekeyCarDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            KeyCarDb::class.java,
            "KeyCarDb"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideKeyCarDao(keyCarDb: KeyCarDb)=keyCarDb.KeyCarDao()
    @Singleton
    @Provides
    fun provideKeyTypeDao(keyCarDb: KeyCarDb)=keyCarDb.KeyTypeDao()

}
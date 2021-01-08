package com.mfathurz.moviecatalogue.core.di

import androidx.room.Room
import com.mfathurz.moviecatalogue.core.BuildConfig
import com.mfathurz.moviecatalogue.core.data.RepositoryImpl
import com.mfathurz.moviecatalogue.core.data.source.local.LocalDataSource
import com.mfathurz.moviecatalogue.core.data.source.local.room.MovieDatabase
import com.mfathurz.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.mfathurz.moviecatalogue.core.data.source.remote.api.ApiService
import com.mfathurz.moviecatalogue.core.domain.repository.IRepository
import com.mfathurz.moviecatalogue.core.utils.Constants
import com.mfathurz.moviecatalogue.core.utils.JsonHelper
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }

    single {
        val passphrase = SQLiteDatabase.getBytes(BuildConfig.LOCAL_DB_ENCRYPT_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_tv_show.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname,"sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .certificatePinner(certificatePinner)
            .build()
    }

    factory { JsonHelper(get()) }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get(), get()) }
    single { LocalDataSource(get()) }
    single<IRepository> {
        RepositoryImpl(get(), get())
    }
}


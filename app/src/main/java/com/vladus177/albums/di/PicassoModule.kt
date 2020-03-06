package com.vladus177.albums.di

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import okhttp3.OkHttpClient
import dagger.Provides
import com.squareup.picasso.Picasso
import dagger.Module

@Module
class PicassoModule {

    @Provides
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }

    @Provides
    fun provideOkHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

}
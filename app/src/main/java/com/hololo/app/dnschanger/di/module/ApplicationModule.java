package com.hololo.app.dnschanger.di.module;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class) // نصب در کامپوننت سراسری Hilt
public class ApplicationModule {

    @Provides
    @Singleton
    public Context provideContext(@ApplicationContext Context context) {
        return context;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}

package com.hololo.app.dnschanger.dnschanger;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;

@Module
@InstallIn(ActivityComponent.class)
public class DNSModule {

    @Provides
    @ActivityScoped
    public IDNSView provideView(Activity activity) {
        return (IDNSView) activity;
    }

    @Provides
    @ActivityScoped
    public IDNSPresenter providePresenter(DNSPresenter presenter) {
        return presenter;
    }
    }

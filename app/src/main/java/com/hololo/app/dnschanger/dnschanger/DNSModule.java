package com.hololo.app.dnschanger.dnschanger;

import android.content.Context;
import android.content.Intent;
import com.google.gson.Gson;
import com.hololo.app.dnschanger.data.DNSSource;
import com.hololo.app.dnschanger.settings.SettingsActivity;
import javax.inject.Inject;

public class DNSPresenter implements IDNSPresenter {

    private final IDNSView view;
    private final DNSSource dnsSource;

    @Inject
    public DNSPresenter(IDNSView view, Context context, Gson gson) {
        this.view = view;
        this.dnsSource = new DNSSource(context, gson);
    }
    
    // بقیه متدهای این کلاس بدون تغییر باقی می‌مانند
    // ...
}

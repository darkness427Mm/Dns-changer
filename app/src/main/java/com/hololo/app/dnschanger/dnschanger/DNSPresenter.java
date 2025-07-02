package com.hololo.app.dnschanger.dnschanger;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.hololo.app.dnschanger.R;
import com.hololo.app.dnschanger.data.DNSSource;
import com.hololo.app.dnschanger.settings.SettingsActivity;
import java.util.List;
import javax.inject.Inject;

public class DNSPresenter implements IDNSPresenter {

    private final IDNSView view;
    private final DNSSource dnsSource;
    private final Context context;

    @Inject
    public DNSPresenter(IDNSView view, Context context, Gson gson) {
        this.view = view;
        this.context = context;
        this.dnsSource = new DNSSource(context, gson);
    }

    @Override
    public void start(String dns1, String dns2) {
        if (DNS.isWorking(context)) {
            dnsSource.stop(context);
            return;
        }

        if (TextUtils.isEmpty(dns1) || TextUtils.isEmpty(dns2)) {
            view.showMessage(context.getString(R.string.empty_dns_message));
        } else {
            dnsSource.start(context, dns1, dns2);
        }
    }

    @Override
    public void resume() {
        view.setStatus(DNS.isWorking(context));
        view.setDns(dnsSource.getDns1(), dnsSource.getDns2());
    }

    @Override
    public void pause() {
        // Nothing to do here
    }

    @Override
    public void showDnsList() {
        List<DNS> list = dnsSource.getDnsList();
        String[] dnsNames = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dnsNames[i] = list.get(i).getName();
        }
        view.showDnsList(dnsNames);
    }

    @Override
    public void showCustomDns() {
        view.showCustomDns(dnsSource.getDns1(), dnsSource.getDns2());
    }

    @Override
    public void launchSettings() {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setDnsFromList(int which) {
        DNS dns = dnsSource.getDnsList().get(which);
        view.setDns(dns.getDns1(), dns.getDns2());
        dnsSource.setDns(dns.getDns1(), dns.getDns2());
        view.hideDnsList();
    }
}

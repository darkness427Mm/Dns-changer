package com.hololo.app.dnschanger.dnschanger;

public interface IDNSPresenter {
    void start(String dns1, String dns2);

    void resume();

    void pause();

    void showDnsList();

    void showCustomDns();

    void launchSettings();

    void setDnsFromList(int which);
}

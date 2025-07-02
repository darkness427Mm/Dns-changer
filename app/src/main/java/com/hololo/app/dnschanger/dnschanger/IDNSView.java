package com.hololo.app.dnschanger.dnschanger;

public interface IDNSView {
    void setDns(String dns1, String dns2);

    void showDnsList(String[] list);

    void hideDnsList();

    void showCustomDns(String dns1, String dns2);

    void hideCustomDns();

    void setStatus(boolean isRunning);

    void showMessage(String message);
}

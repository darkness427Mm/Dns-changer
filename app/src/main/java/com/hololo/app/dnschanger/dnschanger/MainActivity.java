package com.hololo.app.dnschanger.dnschanger;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // <-- import اضافه شده
import androidx.coordinatorlayout.widget.CoordinatorLayout; // <-- import اضافه شده

import com.google.android.material.appbar.CollapsingToolbarLayout; // <-- import اضافه شده
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hololo.app.dnschanger.R;
import com.hololo.app.dnschanger.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements IDNSView, DialogInterface.OnClickListener {

    private ActivityMainBinding binding;
    private AlertDialog dnsListDialog;
    private AlertDialog customDnsDialog;

    @Inject
    IDNSPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.dns1.setMask("###.###.###.###");
        binding.dns2.setMask("###.###.###.###");

        binding.startButton.setOnClickListener(v -> presenter.start(
                binding.dns1.getRawText(),
                binding.dns2.getRawText()
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.dns_list) {
            presenter.showDnsList();
        } else if (id == R.id.custom_dns) {
            presenter.showCustomDns();
        } else if (id == R.id.settings) {
            presenter.launchSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDns(String dns1, String dns2) {
        if (!TextUtils.isEmpty(dns1)) {
            binding.dns1.setText(dns1);
        }
        if (!TextUtils.isEmpty(dns2)) {
            binding.dns2.setText(dns2);
        }
    }

    @Override
    public void showDnsList(String[] list) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle(R.string.dns_list);
        builder.setItems(list, this);
        dnsListDialog = builder.create();
        dnsListDialog.show();
    }

    @Override
    public void hideDnsList() {
        if (dnsListDialog != null && dnsListDialog.isShowing()) {
            dnsListDialog.dismiss();
        }
    }

    @Override
    public void showCustomDns(String dns1, String dns2) {
        // Logic for custom DNS dialog can be implemented here.
    }

    @Override
    public void hideCustomDns() {
        if (customDnsDialog != null && customDnsDialog.isShowing()) {
            customDnsDialog.dismiss();
        }
    }

    @Override
    public void setStatus(boolean isRunning) {
        binding.startButton.setText(isRunning ? R.string.stop : R.string.start);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        presenter.setDnsFromList(which);
    }
}

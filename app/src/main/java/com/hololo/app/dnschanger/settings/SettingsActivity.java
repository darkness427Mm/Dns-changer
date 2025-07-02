package com.hololo.app.dnschanger.settings;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.hololo.app.dnschanger.R;
import dagger.hilt.android.AndroidEntryPoint; // نقطه ویرگول به این خط اضافه شد

@AndroidEntryPoint
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // این یک layout فرضی است
    }
}

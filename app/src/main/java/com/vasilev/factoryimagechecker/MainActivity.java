package com.vasilev.factoryimagechecker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.vasilev.factoryimagechecker.service.FactoryImageCheckerService;
import com.vasilev.factoryimagechecker.util.CheckerTags;

public final class MainActivity extends AppCompatActivity {

    private String tag = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton shamuRadioButton = (RadioButton) findViewById(R.id.radio_shamu);
        if (shamuRadioButton == null)
            throw new IllegalStateException("Layout must contain a RadioButton with id 'radio_shamu'");

        final RadioButton anglerRadioButton = (RadioButton) findViewById(R.id.radio_angler);
        if (anglerRadioButton == null)
            throw new IllegalStateException("Layout must contain a RadioButton with id 'radio_angler'");

        final Button submitButton = (Button) findViewById(R.id.button_submit);
        if (submitButton == null)
            throw new IllegalStateException("Layout must contain a Button with id 'button_submit'");

        shamuRadioButton.setOnClickListener(v -> {
            if (!submitButton.isEnabled()) {
                submitButton.setEnabled(true);
            }
            tag = CheckerTags.SHAMU_CHECKER_TAG;
        });

        anglerRadioButton.setOnClickListener(v -> {
            if (!submitButton.isEnabled()) {
                submitButton.setEnabled(true);
            }
            tag = CheckerTags.ANGLER_CHECKER_TAG;
        });

        submitButton.setOnClickListener(v -> {
            final PeriodicTask task = new PeriodicTask.Builder()
                    .setService(FactoryImageCheckerService.class)
                    .setPeriod(30 * 60)
                    .setTag(tag)
                    .setUpdateCurrent(true)
                    .build();
            GcmNetworkManager.getInstance(MainActivity.this).schedule(task);
            Toast.makeText(MainActivity.this, R.string.scheduled, Toast.LENGTH_LONG).show();
            this.finish();
        });
    }
}

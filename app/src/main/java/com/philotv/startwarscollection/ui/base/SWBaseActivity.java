package com.philotv.startwarscollection.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.mvp.BaseView;

public abstract class SWBaseActivity<T> extends AppCompatActivity implements BaseView<T> {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar);

    }


    @Override
    public void setupToolbar(String title) {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

}

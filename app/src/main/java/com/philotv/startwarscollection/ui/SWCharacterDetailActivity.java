package com.philotv.startwarscollection.ui;

import android.content.Intent;
import android.os.Bundle;

import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.ui.base.SWBaseActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.view.MenuItem;

/**
 * This Activity is used to show selected SWCharacter details, when
 * the app is running on narrow devices( screen size approx. less then 7'').
 */
public class SWCharacterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // only add a fragment, when savedInstanceState is null.
        // If savedInstanceState is not null,
        // Android will automatically add the fragment to the activity
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            Bundle arguments = new Bundle();

            arguments.putString(SWCharacterDetailFragment.SW_CHARACTER_DETAIL,
                    getIntent().getStringExtra(SWCharacterDetailFragment.SW_CHARACTER_DETAIL));

            SWCharacterDetailFragment fragment = new SWCharacterDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, SWMainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

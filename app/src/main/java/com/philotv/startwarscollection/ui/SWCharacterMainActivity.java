package com.philotv.startwarscollection.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.adapter.SWCharacterClientAdapter;
import com.philotv.startwarscollection.data.SWCharacterDataHelper;
import com.philotv.startwarscollection.ui.base.SWCharacterClientBaseActivity;

/**
 * An activity representing a list of Items. This activity
 * has different layout for handset and tablet-size devices.
 * check activity_item_list.xml file for more details.
 */
public class SWCharacterMainActivity extends SWCharacterClientBaseActivity
        implements SWCharacterMainContract.ViewSWCharacterList{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SWCharacterClientAdapter(this, SWCharacterDataHelper.getCharacterList(), mTwoPane));
    }

    @Override
    public void showSWCharacterList() {

    }

    @Override
    public void setPresenter(SWCharacterMainContract.Presenter presenter) {

    }
}

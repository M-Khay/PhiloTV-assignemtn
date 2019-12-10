package com.philotv.startwarscollection.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.bluehomestudio.progressimage.ProgressPicture;
import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.adapter.SWClientAdapter;
import com.philotv.startwarscollection.data.SWCharacter;
import com.philotv.startwarscollection.data.SWCharacterDetail;
import com.philotv.startwarscollection.ui.SWMainContract.ViewSWCharacterList;
import com.philotv.startwarscollection.ui.base.SWBaseActivity;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different layout for handset and tablet-size devices.
 * check activity_item_list.xml file for more details.
 */
public class SWMainActivity<T> extends SWBaseActivity<SWMainContract.Presenter>
        implements ViewSWCharacterList {

    private static final String TAG = SWMainActivity.class.getSimpleName();
    private static final String SAVED_SEARCH = "SAVED_LAST_SEARCH";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    ProgressPicture loadingAnimation;
    SWClientAdapter adapter;

    SWMainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        loadingAnimation = findViewById(R.id.loading_view);
        loadingAnimation.startAnimation();


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        presenter = new SWMainPresenter(this);
        presenter.getSWCharacterList(null);
    }

    @Override
    public void showSWCharacterList(List<SWCharacter> characterList) {

        adapter = new SWClientAdapter(this, characterList, mTwoPane);
        loadingAnimation.stopAnimation();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }


    @Override
    public void setPresenter(SWMainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public SWMainContract.Presenter getPresenter() {
        if (presenter != null) return presenter;
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final StringBuilder queryString = new StringBuilder();


        // Associate searchable configuration with the SearchView
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getSWCharacterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryString.append(newText);
                if (queryString.length() >= 3)
                    presenter.getSWCharacterList(queryString.toString());
                return false;
            }
        });

        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {

        outState.putParcelableArrayList(SAVED_SEARCH, adapter.getSwCharacterList());
    }

}

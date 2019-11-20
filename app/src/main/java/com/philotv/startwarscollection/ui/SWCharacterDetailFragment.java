package com.philotv.startwarscollection.ui;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.adapter.SWCharacterClientBaseFragment;
import com.philotv.startwarscollection.data.SWCharacterDataHelper;
import com.philotv.startwarscollection.data.SWCharacterDetail;

/**
 * Fragment to show  single SWCharacter detail.
 * The fragment is contained in {@link SWCharacterMainActivity}
 * on large screen (Tablets) or a {@link SWCharacterDetailActivity} on handsets.
 */
public class SWCharacterDetailFragment extends SWCharacterClientBaseFragment implements SWCharacterMainContract.ViewSWCharacterDetails {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private SWCharacterDetail mItem;

    public SWCharacterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the user selected SWCharacter details, from the list.
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            // How to pass item
//            mItem = SWCharacterDataHelper.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getSWCharacterName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.getSWCharacterDetail());
        }

        return rootView;
    }

    @Override
    public void showSWCharacterDetail() {

    }

    @Override
    public void setPresenter(SWCharacterMainContract.Presenter presenter) {

    }
}

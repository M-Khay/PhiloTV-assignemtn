package com.philotv.startwarscollection.ui;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bluehomestudio.progressimage.ProgressPicture;
import com.philotv.startwarscollection.BuildConfig;
import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.data.SWCharacterDetail;
import com.philotv.startwarscollection.ui.SWMainContract.ViewSWCharacterDetails;

/**
 * Fragment to show  single SWCharacter detail.
 * The fragment is contained in {@link SWMainActivity}
 * on large screen (Tablets) or a {@link SWCharacterDetailActivity} on handsets.
 */
public class SWCharacterDetailFragment<T> extends Fragment implements SWMainContract.ViewSWCharacterDetails {

    public static final String HOME_PLANET_URL = "HOME_WORLD_URL";
    private String TAG = SWCharacterDetailFragment.class.getSimpleName();

    /**
     * The fragment argument is  a Parcalable Java  object, to show selected character full details.
     */
    SWCharacterDetail characterDetail;
    public static final String SW_CHARACTER_DETAIL= "SW_CHARACTER_DETAIL";  // to get selected character Detailed infomation.

    View rootView;
    SWMainContract.Presenter presenter;
    private ProgressPicture loadingAnimation;

    public SWCharacterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!(savedInstanceState.isEmpty())){
           characterDetail =   savedInstanceState.getParcelable(SW_CHARACTER_DETAIL);
        }else if (getArguments().containsKey(SW_CHARACTER_DETAIL) ) {
            characterDetail = (getArguments().getParcelable(SW_CHARACTER_DETAIL));
        }

        if (characterDetail != null) {
            setupToolbar("Details");
            showSWCharacterDetail(characterDetail);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.character_detail_fragment, container, false);

        loadingAnimation = rootView.findViewById(R.id.loading_view);
        loadingAnimation.startAnimation();
        return rootView;
    }

    @Override
    public void showSWCharacterDetail(SWCharacterDetail characterDetail) {
        loadingAnimation.stopAnimation();
        ((TextView) rootView.findViewById(R.id.id_text)).setText(characterDetail.getSWCharacterName());
    }

    @Override
    public void setPresenter(SWMainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public SWMainContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void setupToolbar(String name) {

    }


    private int extractPlanetId(String homeWorldurl) {

        // SWAPI API : PLANETS API FORMAT :-
        // ENDPOINT : /planets/:id/ -- get a specific planets resource
        CharSequence planetAPIEndpoint = new String(BuildConfig.APP_BASE_URL_SWAPI + "planets/");
        homeWorldurl.trim();

        StringBuilder planetId = new StringBuilder(homeWorldurl.replace(planetAPIEndpoint, ""));
        planetId.deleteCharAt(planetId.length() - 1);

        Log.d(TAG, "SW Character planet id" + planetId);

        return Integer.parseInt(planetId.toString());
    }
}

package com.philotv.startwarscollection.ui;

import androidx.annotation.NonNull;

import com.philotv.startwarscollection.mvp.BaseView;

public class SWCharacterMainPresenter implements SWCharacterMainContract.Presenter {

    BaseView<SWCharacterMainContract.Presenter> view;

    public void SWCharacterMainPreseter(@NonNull BaseView<SWCharacterMainContract.Presenter> view) {
        this.view = view;
        view.setPresenter(this);

    }

    @Override
    public void getSWCharacterList() {
        if (view instanceof SWCharacterMainActivity)
            ((SWCharacterMainActivity) view).showSWCharacterList();
    }

    @Override
    public void getSelectedSWCharacterDetails() {
        if (view instanceof SWCharacterDetailFragment )
            ((SWCharacterMainActivity) view).showSWCharacterList();

    }

    @Override
    public void start() {

    }
}

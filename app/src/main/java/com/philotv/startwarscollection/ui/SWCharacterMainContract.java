package com.philotv.startwarscollection.ui;

import com.philotv.startwarscollection.mvp.BasePresenter;
import com.philotv.startwarscollection.mvp.BaseView;

public interface SWCharacterMainContract {

    interface ViewSWCharacterList extends BaseView<Presenter>{
        void showSWCharacterList();
    }

    interface ViewSWCharacterDetails extends BaseView<Presenter>{
        void showSWCharacterDetail();
    }

    interface Presenter extends BasePresenter{
        void getSWCharacterList();

        void getSelectedSWCharacterDetails();
    }

}

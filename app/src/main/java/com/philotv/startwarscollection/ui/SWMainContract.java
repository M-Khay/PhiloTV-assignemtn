package com.philotv.startwarscollection.ui;

import androidx.annotation.Nullable;

import com.philotv.startwarscollection.adapter.SWClientAdapter;
import com.philotv.startwarscollection.data.SWCharacter;
import com.philotv.startwarscollection.data.SWCharacterDetail;
import com.philotv.startwarscollection.mvp.BasePresenter;
import com.philotv.startwarscollection.mvp.BaseView;

import java.util.List;

public interface SWMainContract {

    interface ViewSWCharacterList extends BaseView<Presenter> {
        void showSWCharacterList(List<SWCharacter> characterList);
    }

    interface ViewSWCharacterDetails extends BaseView<Presenter> {
        void showSWCharacterDetail(SWCharacterDetail swCharacterDetail);
    }

    interface Presenter extends BasePresenter {
        void getSWCharacterList(@Nullable String characterNamePrefix);
        void getSelectedSWCharacterDetails(int planetId);
        void getCharacterImage(String characterName, SWClientAdapter adapter);
    }

}

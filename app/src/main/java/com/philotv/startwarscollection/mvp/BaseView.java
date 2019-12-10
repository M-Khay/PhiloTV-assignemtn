package com.philotv.startwarscollection.mvp;

public interface BaseView<T> {
    void setPresenter(T presenter);

    T getPresenter();

    void setupToolbar(String name);
}

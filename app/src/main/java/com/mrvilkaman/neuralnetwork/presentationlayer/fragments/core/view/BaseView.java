package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view;


import android.content.Context;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.BasePresenter;

public interface BaseView {
    Context getContext();

    BasePresenter getPresenter();

    void showProgress();

    void hideProgress();

    void showError(Throwable throwable);

    void showMessage(int testId);

    void showMessage(String text);

}

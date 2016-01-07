package com.mrvilkaman.neuralnetwork.presentationlayer.activities;


import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.presentationlayer.stubs.Toolbar;

public interface BaseActivityView {

    void showProgress();

    void hideProgress();

    void clearProgress();

    void back();

    void hideKeyboard();

    Toolbar getToolbar();

    IStore getStore();
}

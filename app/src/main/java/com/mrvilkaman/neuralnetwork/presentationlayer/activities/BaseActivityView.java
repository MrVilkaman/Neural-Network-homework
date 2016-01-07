package com.mrvilkaman.neuralnetwork.presentationlayer.activities;


import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.presentationlayer.toolbar.IToolbar;

public interface BaseActivityView {

    void showProgress();

    void hideProgress();

    void clearProgress();

    void back();

    void hideKeyboard();

    IToolbar getToolbar();

    IStore getStore();
}

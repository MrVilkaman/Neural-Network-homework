package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.BasePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeView;

import rx.functions.Action1;

public abstract class RecognizePresenter extends BasePresenter<RecognizeView> {

	public abstract void clickRecognise();

	public abstract void clickRecogniseResult(boolean val);
}

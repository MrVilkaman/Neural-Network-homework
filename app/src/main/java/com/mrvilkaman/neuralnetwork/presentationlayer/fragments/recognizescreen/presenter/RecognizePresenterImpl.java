package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter;

import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

public class RecognizePresenterImpl extends RecognizePresenter {

	@Override
	protected void onViewAttached() {

	}

	@Override
	public void clickRecognise() {

	}

	@Override
	public void clickRecogniseResult(boolean val) {
		Utils.toast(getContext(),Boolean.toString(val));
	}
}

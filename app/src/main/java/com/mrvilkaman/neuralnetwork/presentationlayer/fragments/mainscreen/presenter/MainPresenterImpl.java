package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter;


import com.mrvilkaman.neuralnetwork.datalayer.IStore;

import rx.android.schedulers.AndroidSchedulers;

public class MainPresenterImpl extends MainPresenter {

	private final IStore store;

	public MainPresenterImpl(IStore store) {
		this.store = store;
	}

	@Override
	public void onClickTraining() {
		subscribe(
				store.getImagesTitle()
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(list -> getView().showDialog(list)));
	}

	@Override
	public void openWithChar(CharSequence input) {
		getView().openWithChar(input.charAt(0));
	}

	@Override
	public void onClickRecognize() {
		getView().openRecognize();
	}
}

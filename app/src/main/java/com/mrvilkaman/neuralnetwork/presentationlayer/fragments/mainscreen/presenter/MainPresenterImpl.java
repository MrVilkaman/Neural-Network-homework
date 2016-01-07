package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter;


public class MainPresenterImpl extends MainPresenter {

	@Override
	public void onClickTraining() {
		//// TODO: 07.01.2016 load list
		getView().showDialog();
	}

	@Override
	public void openWithChar(CharSequence input) {
		getView().openWithChar(input.charAt(0));
	}

}

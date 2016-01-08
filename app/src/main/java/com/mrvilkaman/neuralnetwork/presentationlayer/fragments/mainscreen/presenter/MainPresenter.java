package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.BasePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.view.MainView;

public abstract class MainPresenter extends BasePresenter<MainView> {
	public abstract void onClickTraining();

	public abstract void openWithChar(CharSequence input);

	public abstract void onClickRecognize();
}

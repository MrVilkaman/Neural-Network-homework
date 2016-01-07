package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.presenter;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.BasePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.view.TrainingView;

public abstract class TrainingPresenter extends BasePresenter<TrainingView> {

	public abstract void clickRecognise();

	public abstract void clickRecogniseResult(boolean val);
}

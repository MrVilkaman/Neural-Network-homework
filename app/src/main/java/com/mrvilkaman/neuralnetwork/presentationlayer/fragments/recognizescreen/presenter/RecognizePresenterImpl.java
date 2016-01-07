package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter;

import com.mrvilkaman.neuralnetwork.datalayer.Constants;
import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;

public class RecognizePresenterImpl extends TrainingPresenter {

	private Neuron currentNeuron;
	private int[][] lastIntMatrix;

	@Override
	protected void onViewAttached() {
		if (currentNeuron == null) {
			currentNeuron = new Neuron(Constants.SIZE, Constants.SIZE);
		}
	}

	@Override
	public void clickRecognise() {
		lastIntMatrix = Converters.boolToInt(getView().getCellMatrix());
		currentNeuron.mulW(lastIntMatrix);
		currentNeuron.sum();
		getView().showRecogniseResult(currentNeuron.rez(), currentNeuron.getSum());
	}

	@Override
	public void clickRecogniseResult(boolean val) {
		if (!val) {
			if (!currentNeuron.rez()) {
				currentNeuron.incW(lastIntMatrix);
			} else {
				currentNeuron.decW(lastIntMatrix);
			}
			lastIntMatrix = null;
			getView().drawWeight(currentNeuron.getWeight());
		}
	}
}

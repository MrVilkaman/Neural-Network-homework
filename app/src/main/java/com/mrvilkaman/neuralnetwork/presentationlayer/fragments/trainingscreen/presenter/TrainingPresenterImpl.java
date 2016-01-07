package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.presenter;

import com.mrvilkaman.neuralnetwork.datalayer.Constants;
import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;

public class TrainingPresenterImpl extends TrainingPresenter {

	private final IStore store;
	private Neuron currentNeuron;
	private int[][] lastIntMatrix;

	public TrainingPresenterImpl(IStore store) {
		this.store = store;
	}

	@Override
	protected void onViewAttached() {
		if (currentNeuron == null) {
			currentNeuron = new Neuron(Constants.SIZE, Constants.SIZE,getView().getChar());
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

			subscribe(store.saveWeight(currentNeuron).subscribe());
		}
	}


}

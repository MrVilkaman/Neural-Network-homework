package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.presenter;

import com.mrvilkaman.neuralnetwork.datalayer.Constants;
import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
			blockUi();
			subscribe(store.getNeuron(String.valueOf(getView().getChar()))
					.doOnNext(neuron -> currentNeuron = neuron)
					.map(Neuron::getWeight)
					.observeOn(AndroidSchedulers.mainThread())
					.doOnNext(ints -> {
						getView().drawWeight(ints);
						disabledBlock();
					})
					.subscribe());

		}
	}

	private void blockUi() {
		Utils.toast(getContext(),"blockUi");
	}

	private void disabledBlock() {
		Utils.toast(getContext(),"disabledBlock");
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

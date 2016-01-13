package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.presenter;

import com.mrvilkaman.neuralnetwork.datalayer.Constants;
import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;
import com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.engine.GeneticEngine;
import com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.fitness.Fitness;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TrainingPresenterImpl extends TrainingPresenter {

	private final IStore store;
	private Neuron currentNeuron;
	private boolean[][] lastIntMatrix;
	private List<boolean[][]> listWithImage = new ArrayList<>();

	public TrainingPresenterImpl(IStore store) {
		this.store = store;
	}

	@Override
	protected void onViewAttached() {
		if (currentNeuron == null) {
			getView().showProgress();
			subscribe(store.getNeuron(String.valueOf(getView().getChar()))
					.doOnNext(neuron -> currentNeuron = neuron)
					.map(Neuron::getWeight)
					.observeOn(AndroidSchedulers.mainThread())
					.doOnNext(ints -> {
						getView().drawWeight(ints);
						getView().hideProgress();
					})
					.subscribe());

		}
	}

	@Override
	public void clickRecognise() {
		lastIntMatrix = getView().getCellMatrix();
		currentNeuron.mulW(Converters.boolToInt(lastIntMatrix));
		currentNeuron.sum();
		getView().showRecogniseResult(currentNeuron.rez(), currentNeuron.getSum());
	}

	@Override
	public void clickRecogniseResult(boolean val) {
		if (!val) {
			if (!currentNeuron.rez()) {
				currentNeuron.incW(Converters.boolToInt(lastIntMatrix));
			} else {
				currentNeuron.decW(Converters.boolToInt(lastIntMatrix));
			}
			getView().drawWeight(currentNeuron.getWeight());
		}
		listWithImage.add(lastIntMatrix.clone());
		lastIntMatrix = null;
	}

	@Override
	public void saveNeuron() {
//		store.saveWeight(currentNeuron).subscribe();
	}

	@Override
	public void doCross() {
		GeneticEngine ge = new GeneticEngine(new Fitness(currentNeuron));
		ge.setGenerationCount(100); //устанвливаем кол-во поколений
		ge.setUseMutation(true); //наши геномы могут мутировать
		ge.setMutationPercent(0.1f);
		ge.setMutationPercentGenom(0.15f);

		List<boolean[][]> list = getList();
		List<boolean[][]> best = ge.run(list);


//		Neuron newNeuron = new Neuron(Constants.SIZE, Constants.SIZE, 'x');
		for (boolean[][] booleen : best) {
			int[][] inP = Converters.boolToInt(booleen);

			currentNeuron.mulW(inP);
			currentNeuron.sum();
			if (!currentNeuron.rez()) {
				currentNeuron.incW(inP);
			} else {
				currentNeuron.decW(inP);
			}
//			newNeuron.mulW(inP);
//			newNeuron.sum();
//			if (!newNeuron.rez()) {
//				newNeuron.incW(inP);
//			} else {
//				newNeuron.decW(inP);
//			}
//			newNeuron.mulW(inP);
//			newNeuron.sum();
//			if (!newNeuron.rez()) {
//				newNeuron.incW(inP);
//			} else {
//				newNeuron.decW(inP);
//			}
		}
		getView().drawWeight(currentNeuron.getWeight());
	}

	private List<boolean[][]> getList() {

		return listWithImage;
	}
}

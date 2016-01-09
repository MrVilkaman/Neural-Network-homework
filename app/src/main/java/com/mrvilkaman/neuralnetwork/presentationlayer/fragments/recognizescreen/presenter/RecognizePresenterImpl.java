package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter;


import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class RecognizePresenterImpl extends RecognizePresenter {

	private final IStore store;
	private List<Neuron> neurons;

	public RecognizePresenterImpl(IStore store) {
		this.store = store;
	}

	@Override
	protected void onViewAttached() {
		super.onViewAttached();
		if (neurons == null) {
			getView().showProgress();
			subscribe(store.getNeurons()
					.doOnNext(neurons -> this.neurons = neurons)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(neurons1 -> getView().hideProgress()));

		}
	}

	@Override
	public void clickRecognise() {
		getView().showProgress();

		int[][] ints = Converters.boolToInt(getView().getCellMatrix());

//		Observable.just(getView().getCellMatrix())
//				.map(Converters::boolToInt)
		subscribe(Observable.from(neurons)
				.map((neuron) -> {
					neuron.mulW(ints);
					neuron.sum();
					return neuron;
				})
//				.filter(Neuron::rez)
				.toSortedList((neuron, neuron2) -> {
					int lhs = neuron.getSum();
					int rhs = neuron2.getSum();
					return lhs > rhs ? -1 : (lhs == rhs ? 0 : 1);
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(this::print)
				.subscribe(neurons1 -> getView().hideProgress()));
	}

	private void print(List<Neuron> neurons) {
		subscribe(Observable.from(neurons)
				.limit(6)
				.map(neuron -> String.format("%s - %d (%s)", neuron.getName(), neuron.getSum(),neuron.rez()))
//				.map(text -> text + "\n")
				.toList()
				.map(strings -> {
					StringBuilder stringBuilder = new StringBuilder();
					for (String string : strings) {
						stringBuilder.append(string)
								.append('\n');
					}
					return stringBuilder.toString();
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(strings -> getView().printStrings(strings)));
	}
}

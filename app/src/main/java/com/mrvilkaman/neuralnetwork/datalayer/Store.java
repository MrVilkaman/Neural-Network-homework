package com.mrvilkaman.neuralnetwork.datalayer;


import android.content.Context;

import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.FileUtils;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Store implements IStore {

	private Context context;

	public Store(Context context) {
		this.context = context;
	}

	@Override
	public Observable<List<String>> getImagesTitle() {
		return FileUtils.getTitles(context);
	}

	@Override
	public Observable<Void> saveWeight(Neuron neuron) {
		return Observable.defer(() -> Observable.just(neuron))
				.doOnNext(n -> FileUtils.storeOO(context, String.valueOf(neuron.getName()),neuron.getWeight()))
				.map((Func1<Neuron, Void>) neuron1 -> null)
				.subscribeOn(Schedulers.io());

	}

	@Override
	public Observable<Neuron> getNeuron(String name) {
		return Observable.defer(() -> Observable.just(name))
				.map(s -> FileUtils.getOO(context,s))
				.onErrorReturn(throwable -> new int[Constants.SIZE][Constants.SIZE])
				.map(ints -> new Neuron(ints, name.charAt(0)))
				.subscribeOn(Schedulers.io());
	}

	@Override
	public Observable<List<Neuron>> getNeurons() {
		return getImagesTitle()
				.flatMap(Observable::from)
				.flatMap(Store.this::getNeuron)
				.toList()
				.subscribeOn(Schedulers.io());
	}
}

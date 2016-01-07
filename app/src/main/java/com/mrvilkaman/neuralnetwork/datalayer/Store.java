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
}

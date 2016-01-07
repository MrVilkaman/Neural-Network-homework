package com.mrvilkaman.neuralnetwork.datalayer;


import android.content.Context;

import com.mrvilkaman.neuralnetwork.presentationlayer.utils.FileUtils;
import java.util.List;

import rx.Observable;

public class Store implements IStore {

	private Context context;

	public Store(Context context) {
		this.context = context;
	}

	@Override
	public Observable<List<String>> getImagesTitle() {
		return FileUtils.getTitles(context);
	}
}

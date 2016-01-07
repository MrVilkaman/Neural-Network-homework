package com.mrvilkaman.neuralnetwork.presentationlayer.utils;

import android.content.Context;

import java.io.File;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class FileUtils {

	public FileUtils() {
	}

	public static Observable<List<String>> getTitles(Context context) {
		return Observable.defer(() -> Observable.just(StorageUtils.getStoragePath(context)))
				.map(File::new)
				.map(File::listFiles)
				.flatMap(Observable::from)
				.map(File::getName)
				.toList()
				.subscribeOn(Schedulers.io());
	}
}

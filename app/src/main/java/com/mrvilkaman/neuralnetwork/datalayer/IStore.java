package com.mrvilkaman.neuralnetwork.datalayer;

import java.util.List;

import rx.Observable;

public interface IStore {

	Observable<List<String>> getImagesTitle();
}

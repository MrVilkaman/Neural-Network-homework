package com.mrvilkaman.neuralnetwork.datalayer;

import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;

import java.util.List;

import rx.Observable;

public interface IStore {

	Observable<List<String>> getImagesTitle();

	Observable<Void> saveWeight(Neuron neuron);

	Observable<Neuron> getNeuron(String name);

	Observable<List<Neuron>> getNeurons();
}

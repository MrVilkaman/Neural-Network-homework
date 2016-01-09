package com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.fitness;

import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.Converters;

/**
 * Created by Zahar on 09.01.2016.
 */
public class Fitness implements IFitnessFunction {


	private final Neuron neuron;

	public Fitness(Neuron neuron){
		this.neuron = neuron;
	}

	@Override
	public long run(boolean[][] genom) {
		neuron.mulW(Converters.boolToInt(genom));
		neuron.sum();
		return neuron.getSum();
	}
}

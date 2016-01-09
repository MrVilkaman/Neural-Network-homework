package com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.engine;

import com.mrvilkaman.neuralnetwork.datalayer.entity.Neuron;
import com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.fitness.Fitness;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticEngineTest extends TestCase {

	private Random random;

	@Test
	public void testRun() throws Exception {
		random = new Random();

		int[][] ints = new int[][]{
			{-5,3,5},
			{-5,1,5},
			{-5,-5,5},
		};
		Neuron neuron = new Neuron(ints,'h');

		GeneticEngine ge = new GeneticEngine(new Fitness(neuron));
		ge.setGenerationCount(10); //устанвливаем кол-во поколений
		ge.setUseMutation(true); //наши геномы могут мутировать
		ge.setMutationPercent(0.1f);
		ge.setMutationPercentGenom(0.15f);

		ArrayList<boolean[][]> imageList = new ArrayList<>();
		imageList.add(new boolean[][]{
				{false,false,true},
				{false,false,true},
				{false,false,true},
		});
		imageList.add(new boolean[][]{
				{false,false,true},
				{false,true,true},
				{false,false,true},
		});
		imageList.add(new boolean[][]{
				{false,true,true},
				{false,false,true},
				{false,false,true},
		});
		imageList.add(getRandom());
		imageList.add(getRandom());
		imageList.add(getRandom());
		imageList.add(getRandom());
		imageList.add(getRandom());

		List<boolean[][]> better = ge.run(imageList); //запускаем

		assertEquals(false,false);

	}

	public boolean[][] getRandom() {
		return new boolean[][]{
				{random.nextBoolean(),random.nextBoolean(),random.nextBoolean()},
				{random.nextBoolean(),random.nextBoolean(),random.nextBoolean()},
				{random.nextBoolean(),random.nextBoolean(),random.nextBoolean()},
		};
	}
}
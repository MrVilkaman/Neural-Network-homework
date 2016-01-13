package com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.engine;

import com.mrvilkaman.neuralnetwork.domainlayer.geneticalgorithms.fitness.IFitnessFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import rx.Observable;

public class GeneticEngine {

	private final IFitnessFunction fitnessFunction;
	private List<boolean[][]> imageListParent;
	private List<boolean[][]> currentImageList;

	private long generationCount; //Кол-во поколений
	private boolean useMutation; //Использовать мутацю
	private double mutationPercent; //Как часто происходит мутация
	private double mutationPercentGenom;
	private int currentGeneration;

	public GeneticEngine(IFitnessFunction fitnessFunction) {
		this.fitnessFunction = fitnessFunction;
	}

	public List<boolean[][]> run(List<boolean[][]> imageList) {
		currentImageList = new ArrayList<>();
		this.imageListParent = cloneList(imageList);

		while (currentGeneration < generationCount) {

			selection();
			crossing();
			if (useMutation) {
				mutation();
			}

//			List<boolean[][]> tmp = imageListParent;
//			imageListParent = currentImageList;
//			currentImageList = tmp;
			Observable.merge(
					Observable.from(currentImageList),
					Observable.from(this.imageListParent))
					.toSortedList((lh, rh) -> {
						long lhs = fitnessFunction.run(lh);
						long rhs = fitnessFunction.run(rh);
						return lhs > rhs ? -1 : (lhs == rhs ? 0 : 1);
					})
					.flatMap(Observable::from)
					.limit(imageList.size())
					.toList()
					.subscribe(list ->imageListParent = cloneList(list));

			currentGeneration++;
		}

		Observable.merge(
				Observable.from(imageList),
				Observable.from(this.imageListParent))
				.toSortedList((lh, rh) -> {
					long lhs = fitnessFunction.run(lh);
					long rhs = fitnessFunction.run(rh);
					return lhs > rhs ? -1 : (lhs == rhs ? 0 : 1);
				})
				.flatMap(Observable::from)
				.limit(imageList.size())
				.toList()
				.subscribe(list ->imageListParent = cloneList(list));


		return imageListParent;
	}

	public List<boolean[][]> cloneList(List<boolean[][]> list) {
		List<boolean[][]> clone = new ArrayList<>(list.size());
		for(boolean[][] item: list) clone.add(cloneArray(item));
		return clone;
	}

	private void selection(){
		currentImageList.clear();
		Random random = new Random();
		for (int i = 0; i< imageListParent.size(); i++) {
			int index1 = random.nextInt(imageListParent.size());
			int index2 = random.nextInt(imageListParent.size());
			long fr1 = fitnessFunction.run(imageListParent.get(index1));
			long fr2 = fitnessFunction.run(imageListParent.get(index2));

			boolean[][] object = fr1 > fr2 ? imageListParent.get(index1) : imageListParent.get(index2);
			currentImageList.add(cloneArray(object));
		}
	} //Процедура селекци
	private void crossing() {
		for (int i = 0; i < imageListParent.size() / 2; i++) {
			int index1 = i << 1;
			int index2 = index1 | 1;
			cross(currentImageList.get(index1), currentImageList.get(index2));
		}
	} //Процедура скрещивания

	private void cross(boolean[][] left, boolean[][] right) {
		Random random = new Random();
		for (int i = 0; i < left.length; i++) {
			for (int j = 0; j < left[0].length; j++) {
				if (random.nextBoolean()) {
					boolean flag = right[i][j];
					right[i][j] = left[i][j];
					left[i][j] = flag;
				}
			}
		}
	}

	private boolean[][] cloneArray(boolean[][] matrix){
		boolean [][] myInt = new boolean[matrix.length][];
		for(int i = 0; i < matrix.length; i++)
			myInt[i] = matrix[i].clone();
		return myInt;
	}

	private void mutation() {
		Random random = new Random();
		for (boolean[][] booleen : currentImageList) {
			if (random.nextDouble() <= mutationPercentGenom) {
				mutate(booleen);
			}
		}
	}

	private void mutate(boolean[][] booleen) {
		Random random = new Random();
		for (int i = 0; i < booleen.length; i++) {
			for (int j = 0; j < booleen[0].length; j++) {
				if (random.nextDouble() <= mutationPercent) {
					booleen[i][j] = !booleen[i][j];
				}
			}
		}
	}

	public long getGenerationCount() {
		return generationCount;
	}

	public void setGenerationCount(long generationCount) {
		this.generationCount = generationCount;
	}

	public boolean isUseMutation() {
		return useMutation;
	}

	public void setUseMutation(boolean useMutation) {
		this.useMutation = useMutation;
	}

	public double getMutationPercent() {
		return mutationPercent;
	}

	public void setMutationPercent(double mutationPercent) {
		this.mutationPercent = mutationPercent;
	}

	public double getMutationPercentGenom() {
		return mutationPercentGenom;
	}

	public void setMutationPercentGenom(double mutationPercentGenom) {
		this.mutationPercentGenom = mutationPercentGenom;
	}

	public int getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(int currentGeneration) {
		this.currentGeneration = currentGeneration;
	}
}

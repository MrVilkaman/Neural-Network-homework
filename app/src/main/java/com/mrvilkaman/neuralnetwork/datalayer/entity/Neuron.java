package com.mrvilkaman.neuralnetwork.datalayer.entity;


import com.mrvilkaman.neuralnetwork.datalayer.Constants;

public class Neuron {

	private char name;
	private int[][] mul; // Тут будем хранить отмасштабированные сигналы
	private int[][] weight; // Массив для хранения весов
	private int limit = Constants.NEURON_LIMIT; // Порог - выбран экспериментально, для быстрого обучения
	private int sum; // Тут сохраним сумму масштабированных сигналов

	public Neuron(int sizex, int sizey,char name) { // Задаем свойства при создании объекта
		weight = new int[sizex][sizey]; // Определяемся с размером массива (число входов)
		mul = new int[sizex][sizey];
		this.name = name;
	}

	public void mulW(int[][] input) {
		for (int x = 0; x < mul.length; x++) {
			for (int y = 0; y < mul[0].length; y++) {
				// Умножаем его сигнал (0 или 1) на его собственный вес и сохраняем в массив.
				mul[x][y] = 0 < input[x][y] ? weight[x][y] : 0;
			}
		}
	}

	public void sum() {
		sum = 0;
		for (int x = 0; x < mul.length; x++) {
			for (int y = 0; y < mul[0].length; y++) {
				sum += mul[x][y];
			}
		}
	}

	public boolean rez() {
		return sum >= limit;
	}

	public void incW(int[][] inP) {
		for (int x = 0; x < inP.length; x++) {
			for (int y = 0; y < inP[0].length; y++) {
				weight[x][y] += inP[x][y];
			}
		}
	}

	public void decW(int[][] inP) {
		for (int x = 0; x < inP.length; x++) {
			for (int y = 0; y < inP[0].length; y++) {
				weight[x][y] -= inP[x][y];
			}
		}
	}

	public int[][] getWeight() {
		return weight;
	}

	public int getSum() {
		return sum;
	}

	public char getName() {
		return name;
	}
}

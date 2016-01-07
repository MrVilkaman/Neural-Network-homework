package com.mrvilkaman.neuralnetwork.domainlayer;


public class Converters {
	public Converters() {
	}

	public static int[][] boolToInt(boolean[][] matrix) {
		int[][] newM = new int[matrix[0].length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newM[i][j] = matrix[i][j] ? 1 : -1;
			}
		}
		return newM;
	}
}

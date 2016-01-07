package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.view;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseView;

public interface TrainingView extends BaseView {

	boolean[][] getCellMatrix();
	void drawWeight(int[][] weight);
	void showRecogniseResult(boolean rez, int sum);

	char getChar();
}
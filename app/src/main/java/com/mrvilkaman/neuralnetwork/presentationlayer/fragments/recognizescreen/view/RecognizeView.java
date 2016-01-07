package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseView;

public interface RecognizeView extends BaseView {

	boolean[][] getCellMatrix();
	void drawWeight(int[][] weight);
	void showRecogniseResult(boolean rez, int sum);
}
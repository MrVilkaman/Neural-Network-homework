package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseView;

import java.util.List;


public interface RecognizeView extends BaseView {

	boolean[][] getCellMatrix();

	void printStrings(String text);
}
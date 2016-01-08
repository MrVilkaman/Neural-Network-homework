package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.view;


import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseView;

import java.util.List;

public interface MainView extends BaseView {

	void showDialog(List<String> list);

	void openWithChar(char charAt);

	void openRecognize();
}
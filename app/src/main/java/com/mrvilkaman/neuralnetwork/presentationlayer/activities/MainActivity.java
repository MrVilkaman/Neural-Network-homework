package com.mrvilkaman.neuralnetwork.presentationlayer.activities;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeFragment;

public class MainActivity extends BaseActivity{

	@Override
	protected BaseFragment createStartFragment() {
		return RecognizeFragment.open();
	}

}

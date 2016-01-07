package com.mrvilkaman.neuralnetwork.presentationlayer.activities;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.toolbar.IToolbar;

public class MainActivity extends BaseActivity {

	@Override
	protected IToolbar.OnHomeClick getHomeButtonListener() {
		return () -> {
			if (hasChild()) {
				back();
				if (hasChild()) {
					getToolbar().showBackIcon();
				}else{
					getToolbar().showHomeIcon();
				}
			}
		};
	}

	@Override
	protected BaseFragment createStartFragment() {
		return RecognizeFragment.open('1');
	}

}

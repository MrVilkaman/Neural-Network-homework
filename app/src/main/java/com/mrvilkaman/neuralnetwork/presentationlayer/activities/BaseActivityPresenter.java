package com.mrvilkaman.neuralnetwork.presentationlayer.activities;


import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;

public interface BaseActivityPresenter {

	void loadRootFragment(BaseFragment fragment, boolean addToBackStack, boolean isRoot, boolean b);
}

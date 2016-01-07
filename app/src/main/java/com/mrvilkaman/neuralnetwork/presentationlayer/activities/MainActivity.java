package com.mrvilkaman.neuralnetwork.presentationlayer.activities;

import android.os.Bundle;

import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.datalayer.Store;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.view.MainFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.toolbar.IToolbar;

public class MainActivity extends BaseActivity {

	private Store store;

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
		return MainFragment.open();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		store = new Store(getApplicationContext());
	}

	@Override
	public IStore getStore() {
		return store;
	}
}

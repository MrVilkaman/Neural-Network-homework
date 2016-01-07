package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;/**
 * Created by Zahar on 07.01.2016.
 */

import android.os.Bundle;
import android.view.View;

import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenterImpl;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeView;

public class RecognizeFragment extends BaseFragment<RecognizePresenter> implements RecognizeView {


	public static RecognizeFragment open() {
		return new RecognizeFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_recognize;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected RecognizePresenter newPresenter() {
		return new RecognizePresenterImpl();
	}
}
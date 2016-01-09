package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenterImpl;
import com.mrvilkaman.neuralnetwork.presentationlayer.views.NetImageView;

import butterknife.Bind;

public class RecognizeFragment extends BaseFragment<RecognizePresenter> implements RecognizeView {


	@Bind(R.id.recognise)
	Button recognise;
	@Bind(R.id.text_result)
	TextView textResults;
	@Bind(R.id.net_image_view)
	NetImageView netImageView;
	@Bind(R.id.text_result_fields)
	TextView textResultFields;

	public static RecognizeFragment open() {
		return new RecognizeFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_recognize;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {
		getToolbar().setText(R.string.layout_recognize_title);
		getPresenter().subscribe(
				RxView.clicks(recognise)
						.doOnNext(act -> getPresenter().clickRecognise())
						.subscribe());
	}

	@Override
	protected RecognizePresenter newPresenter() {
		return new RecognizePresenterImpl(getStore());
	}

	@Override
	public boolean[][] getCellMatrix() {
		return netImageView.getCellMatrix();
	}

	@Override
	public void printStrings(String text) {
		textResultFields.setText(text);
	}
}
package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenterImpl;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeView;
import com.mrvilkaman.neuralnetwork.presentationlayer.views.NetImageView;
import com.mrvilkaman.neuralnetwork.presentationlayer.views.TextGridView;

import butterknife.Bind;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Observers;

public class RecognizeFragment extends BaseFragment<RecognizePresenter> implements RecognizeView {

	private static final String EXTRA_CHAR = "EXTRA_CHAR";

	@Bind(R.id.recognise_true)
	Button recogniseTrue;
	@Bind(R.id.recognise)
	Button recognise;
	@Bind(R.id.recognise_false)
	Button recogniseFalse;
	@Bind(R.id.text_result)
	TextView textResults;
	@Bind(R.id.net_image_view)
	NetImageView netImageView;
	@Bind(R.id.draw_weight)
	TextGridView gridWeight;
	private char currentChar;

	public static RecognizeFragment open(char ch) {
		RecognizeFragment recognizeFragment = new RecognizeFragment();
		Bundle args = new Bundle();
		args.putChar(EXTRA_CHAR,ch);
		recognizeFragment.setArguments(args);
		return recognizeFragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentChar = getArguments().getChar(EXTRA_CHAR);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_recognize;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {
		getToolbar().setText(String.valueOf(currentChar));
		getPresenter().subscribe(
				RxView.clicks(recognise)
						.doOnNext(act -> getPresenter().clickRecognise())
						.subscribe(v -> {
							recogniseTrue.setEnabled(true);
							recogniseFalse.setEnabled(true);
							recognise.setEnabled(false);
							netImageView.setEnabled(false);
						}));

		getPresenter().subscribe(
				Observable.merge(
						RxView.clicks(recogniseFalse).map(a -> false),
						RxView.clicks(recogniseTrue).map(a -> true))
						.doOnNext(val -> getPresenter().clickRecogniseResult(val))
						.subscribe(val -> {
							recogniseTrue.setEnabled(false);
							recogniseFalse.setEnabled(false);
							recognise.setEnabled(true);
							netImageView.setEnabled(true);
						}));
	}

	@Override
	protected RecognizePresenter newPresenter() {
		return new RecognizePresenterImpl();
	}

	@Override
	public boolean[][] getCellMatrix() {
		return netImageView.getCellMatrix();
	}

	@Override
	public void drawWeight(int[][] weight) {
		gridWeight.setNewValues(weight);
	}

	@Override
	public void showRecogniseResult(boolean rez, int sum) {
		textResults.setText(getString((rez ? R.string.res_true : R.string.res_false), sum));
	}
}
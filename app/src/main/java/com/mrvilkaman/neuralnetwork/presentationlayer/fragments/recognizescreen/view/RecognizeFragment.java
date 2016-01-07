package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view;/**
 * Created by Zahar on 07.01.2016.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.presenter.RecognizePresenterImpl;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeView;

import butterknife.Bind;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Observers;

public class RecognizeFragment extends BaseFragment<RecognizePresenter> implements RecognizeView {

	@Bind(R.id.recognise_true)
	Button recogniseTrue;
	@Bind(R.id.recognise)
	Button recognise;
	@Bind(R.id.recognise_false)
	Button recogniseFalse;
	@Bind(R.id.net_image_view)
	View netImageView;

	public static RecognizeFragment open() {
		return new RecognizeFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_recognize;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {
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
}
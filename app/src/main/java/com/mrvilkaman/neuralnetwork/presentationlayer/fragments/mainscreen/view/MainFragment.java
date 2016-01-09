package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.view;
/**
 * Created by Zahar on 07.01.2016.
 */

import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter.DialogAdapter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter.MainPresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter.MainPresenterImpl;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.recognizescreen.view.RecognizeFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.trainingscreen.view.TrainingFragment;

import java.util.List;

import butterknife.OnClick;

public class MainFragment extends BaseFragment<MainPresenter> implements MainView {


	public static MainFragment open() {
		return new MainFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_main;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected MainPresenter newPresenter() {
		return new MainPresenterImpl(getStore());
	}

	@OnClick(R.id.menu_training)
	void onClickTraining() {
		getPresenter().onClickTraining();
	}

	@OnClick(R.id.menu_recognize)
	void onClickRecognize() {
		getPresenter().onClickRecognize();
	}

	@Override
	public void showDialog(List<String> list) {
		MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
		builder.positiveText(R.string.dialog_new)
				.adapter(new DialogAdapter(getContext(), list),
						(dialog, itemView, which, text) -> {
							getPresenter().openWithChar(text);
							dialog.dismiss();
						})
				.onPositive((materialDialog, dialogAction) -> {
					showEnterDialog();
					materialDialog.dismiss();
				})
				.show();
	}

	@Override
	public void openWithChar(char charAt) {
		showFragment(TrainingFragment.open(charAt));
	}

	@Override
	public void openRecognize() {
		showFragment(RecognizeFragment.open());
	}

	private void showEnterDialog() {
		MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
		builder.inputType(InputType.TYPE_CLASS_TEXT )
				.inputRange(1, 1)
				.input(R.string.input_hint, R.string.input_prefill, (dialog, input) -> {
					getPresenter().openWithChar(input);
					dialog.dismiss();
				})
				.show();
	}
}
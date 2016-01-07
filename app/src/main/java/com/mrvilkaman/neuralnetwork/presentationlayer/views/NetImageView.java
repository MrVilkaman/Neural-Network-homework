package com.mrvilkaman.neuralnetwork.presentationlayer.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.datalayer.Constants;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetImageView extends LinearLayout {

	@Bind(R.id.draw)
	DrawImageView drawImageView;

	private boolean editMode;

	public NetImageView(Context context) {
		super(context);
		init(null);
	}

	public NetImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public NetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_net_image, this, true);
		ButterKnife.bind(this,this);

		drawImageView.setNumColumns(Constants.SIZE);
		drawImageView.setNumRows(Constants.SIZE);

		checkAttrs(attrs);

		if (!editMode) {
			//// TODO: 07.01.2016 hide UI
		}
	}

	private void checkAttrs(AttributeSet attrs) {
		if (attrs == null) {
			return;
		}
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NetImageView, 0, 0);
		try {
			editMode = ta.getBoolean(R.styleable.NetImageView_editmode, true);
		} finally {
			ta.recycle();
		}
	}

	@OnClick(R.id.clear_btn)
	void onClickClear(){
		drawImageView.clear();
	}

	@OnClick(R.id.color_white)
	void onClickWhite(){
		drawImageView.setBlack(false);
	}

	@OnClick(R.id.color_black)
	void onClickBlack(){
		drawImageView.setBlack(true);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		ViewGroup viewGroup = (ViewGroup) getChildAt(0);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setEnabled(enabled);
		}
	}

	public boolean[][] getCellMatrix(){
		return drawImageView.getCellMatrix();
	}
}

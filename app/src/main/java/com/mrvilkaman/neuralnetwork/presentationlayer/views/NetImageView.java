package com.mrvilkaman.neuralnetwork.presentationlayer.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetImageView extends LinearLayout {

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
		Utils.toast(getContext(),"Clear");
	}

	@OnClick(R.id.color_white)
	void onClickWhite(){
		Utils.toast(getContext(),"White");
	}

	@OnClick(R.id.color_black)
	void onClickBlack(){
		Utils.toast(getContext(),"Black");
	}
}

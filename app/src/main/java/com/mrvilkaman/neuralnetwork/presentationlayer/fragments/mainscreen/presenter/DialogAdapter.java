package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.mainscreen.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.MyBaseAdapter;

import java.util.List;

import butterknife.ButterKnife;

public class DialogAdapter extends MyBaseAdapter {

	public DialogAdapter(Context context, List<String> items) {
		super(context);
		addAll(items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
			convertView.setTag(new Holder(convertView));
		}
		Holder holder = (Holder)convertView.getTag();
		holder.text.setText(getItem(position).toString());

		return convertView;
	}

	static class Holder {
		TextView text;
		public Holder(View convertView) {
			text = ButterKnife.findById(convertView,android.R.id.text1);
		}
	}
}

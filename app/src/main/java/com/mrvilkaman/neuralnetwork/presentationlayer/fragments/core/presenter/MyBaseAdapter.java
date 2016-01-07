package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MyBaseAdapter<Obj> extends BaseAdapter {

    protected final LayoutInflater layoutInflater;
    protected final ArrayList<Obj> objects;

    public MyBaseAdapter(Context context) {
        objects = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Obj getItem(final int position) {
		if( position < objects.size()){
			return objects.get(position);
		}
		return null;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    public void addAll(final List<Obj> objList) {
        objects.addAll(objList);
        notifyDataSetChanged();
    }

    public void clear() {
        objects.clear();
    }

    public List<Obj> getObjects() {
        return objects;
    }

    public Obj getFirst() {
        return objects.size() != 0 ? objects.get(0) : null;
    }

    public Obj getLast() {
        int count = objects.size() - 1;
        return 0 <=  count ? getItem(count) : null;
    }
}

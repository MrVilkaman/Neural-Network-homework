package com.mrvilkaman.neuralnetwork.presentationlayer.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrvilkaman.neuralnetwork.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarImpl implements IToolbar {

    private final View view;

    @Bind(R.id.toolbar_title)
    TextView title;

    @Bind(R.id.toolbar_button_place)
    LinearLayout layout;

    @Bind(R.id.toolbar_home_btn)
    ImageButton homeBtn;

    private final OnHomeClick click;

    public ToolbarImpl(View view, OnHomeClick click) {
        ButterKnife.bind(this,view);
        this.view = view;
        this.click = click;
    }

    @Override
    public void hide() {
        view.setVisibility(View.GONE);
    }

	@Override
    public void setText(String text) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
        title.setText(text);
    }

    @Override
    public void setText(int text) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
        title.setText(text);
    }

    @Override
    public void addView(int layoutId, OnToolbarListener viewCreated) {
		clear();
		View view =  LayoutInflater.from(layout.getContext()).inflate(layoutId,layout,false);
        viewCreated.onToolbarViewCreated(view);
        layout.addView(view);
    }

    @Override
    public void clear() {
        for (int i = layout.getChildCount() - 1; i >= 1; i--) {
            layout.removeViewAt(i);
        }
    }

    @Override
    public void hideHomeIcon() {
        homeBtn.setVisibility(View.GONE);
    }

    @Override
    public void showHomeIcon() {
        hideHomeIcon();
//        homeBtn.setImageResource(R.drawable.ic_back);
//        homeBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBackIcon() {
        homeBtn.setImageResource(R.drawable.ic_back);
        homeBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.toolbar_home_btn)
    void homeClick(){
        if (click != null) {
            click.onClick();
        }
    }
}

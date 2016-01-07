package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.presentationlayer.activities.BaseActivityPresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.activities.BaseActivityView;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter.BasePresenter;
import com.mrvilkaman.neuralnetwork.presentationlayer.stubs.Toolbar;
import com.mrvilkaman.neuralnetwork.presentationlayer.utils.Utils;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, BaseActivityView {

    private String previousFragment;
    private P relationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        if (isWorkCall()) {
            getPresenter().setView(this);
            onCreateView(view, savedInstanceState);
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        getPresenter().setView(null);
        super.onDestroyView();
    }

    @Override
    public void back() {
        getActivityView().back();
    }

    @Override
    public void hideKeyboard() {
        getActivityView().hideKeyboard();
    }

    @Override
    public void showProgress() {
        getActivityView().showProgress();
    }

    @Override
    public void hideProgress() {
        getActivityView().hideProgress();
    }

    @Override
    public void clearProgress() {
        getActivityView().clearProgress();
    }

    private boolean isWorkCall() {
        return true;
    }

    public String getPreviousFragment() {
        return previousFragment;
    }

    public void setPreviousFragment(String previousFragment) {
        this.previousFragment = previousFragment;
    }

    protected abstract void onCreateView(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected void showFragment(BaseFragment fragment) {
        getBaseActivity().loadRootFragment(fragment, true, false, false);
    }

    protected void showRootFragment(BaseFragment fragment) {
        getBaseActivity().loadRootFragment(fragment, false, true, false);
    }

    protected void showFragmentWithoutBackStack(BaseFragment fragment) {
        getBaseActivity().loadRootFragment(fragment, false, true, false);
    }

    protected BaseActivityPresenter getBaseActivity() {
        return (BaseActivityPresenter) getActivity();
    }

    protected BaseActivityView getActivityView() {
        return (BaseActivityView) getActivity();
    }

    protected abstract P newPresenter();

    @Override
    public P getPresenter() {
        if (relationPresenter == null) {
            relationPresenter = newPresenter();
        }
        return relationPresenter;
    }

    @Override
    public Toolbar getToolbar() {
        return getActivityView().getToolbar();
    }

    @Override
    public IStore getStore() {
        return getActivityView().getStore();
    }


    @Override
    public void showError(Throwable throwable) {
        hideProgress();
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int testId) {
        Utils.showAlert(getContext(), testId);
    }

    @Override
    public void showMessage(String text) {
        Utils.showAlert(getContext(), text);
    }


}

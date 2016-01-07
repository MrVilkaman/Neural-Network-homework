package com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.presenter;

import android.content.Context;

import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseView;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends BaseView> {

    private CompositeSubscription subscriptions;
    private V view;

    protected void onViewBeforeDetached() {
    }

    public final <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        subscriptions.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer)
        );
    }

    public final <T> void subscribe(Subscription observable) {
        subscriptions.add(observable);
    }

    protected void onViewAttached(){

    }

    protected void onViewDetached() {
    }

    public final V getView() {
        return view;
    }

    public final void setView(V view) {
        if (view == null) {
            subscriptions.unsubscribe();
            onViewBeforeDetached();
            this.view = view;
            onViewDetached();
        } else {
            this.view = view;
            subscriptions = new CompositeSubscription();
            onViewAttached();
        }
    }

    public final Context getContext() {
        return view == null ? null : view.getContext();
    }

    protected void onError(Throwable e) {

    }
}

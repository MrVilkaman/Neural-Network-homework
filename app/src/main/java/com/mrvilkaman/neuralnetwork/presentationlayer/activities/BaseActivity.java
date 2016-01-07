package com.mrvilkaman.neuralnetwork.presentationlayer.activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mrvilkaman.neuralnetwork.R;
import com.mrvilkaman.neuralnetwork.datalayer.IStore;
import com.mrvilkaman.neuralnetwork.presentationlayer.fragments.core.view.BaseFragment;
import com.mrvilkaman.neuralnetwork.presentationlayer.toolbar.IToolbar;
import com.mrvilkaman.neuralnetwork.presentationlayer.toolbar.ToolbarImpl;

import java.util.List;


public abstract class BaseActivity extends AppCompatActivity implements BaseActivityPresenter,BaseActivityView {


	private BaseFragment nextFragment;

	private boolean backStack;
	private boolean isRoot;
	private boolean forceLoad;
	private boolean doubleBackToExitPressedOnce;
	private IToolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = new ToolbarImpl(findViewById(R.id.toolbar), getHomeButtonListener());
		FragmentManager fm = getSupportFragmentManager();
		Fragment contentFragment = fm.findFragmentById(getContainerID());
		if (contentFragment == null) {
			loadRootFragment(createStartFragment(), true, true, false);
		}
	}

	protected abstract IToolbar.OnHomeClick getHomeButtonListener();

	protected abstract BaseFragment createStartFragment();

	protected int getContainerID() {
		return R.id.content;
	}

	@Override
	public void loadRootFragment(BaseFragment fragment, boolean addToBackStack, boolean isRoot, boolean forceLoad) {
		nextFragment = fragment;
		backStack = addToBackStack;
		this.isRoot = isRoot;
		this.forceLoad = forceLoad;
		nextFragment();
	}

	private void exit() {
		if (doubleBackToExitPressedOnce) {
			finish();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Еще раз", Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 1000);
	}

	protected boolean hasChild() {
		return 1 < getSupportFragmentManager().getBackStackEntryCount();
	}


	@Override
	public void onBackPressed() {
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		BaseFragment current = (BaseFragment) supportFragmentManager.findFragmentById(getContainerID());
		if (current != null && current.getPreviousFragment() != null) {
			supportFragmentManager.popBackStack(current.getPreviousFragment(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
			if (hasChild()) {
				getToolbar().showBackIcon();
			}else{
				getToolbar().showHomeIcon();
			}
		} else {
			exit();
		}
	}

	void nextFragment() {
		if (nextFragment != null) {
			BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(getContainerID());
			boolean hasOldFragment = currentFragment != null;
			boolean isAlreadyLoaded = false;
			if (hasOldFragment) {
				isAlreadyLoaded = currentFragment.getClass().getSimpleName().equals(nextFragment.getClass().getSimpleName());
			}

			if (!(hasOldFragment && isAlreadyLoaded)) {
				if (isRoot) {
					clearBackStack();
					getToolbar().showHomeIcon();
				}else{
					getToolbar().showBackIcon();
				}
				FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
				boolean b = backStack || isRoot;
				fragmentTransaction.replace(getContainerID(), nextFragment, nextFragment.getClass().getSimpleName());
				if (currentFragment != null && !isRoot) {
					nextFragment.setPreviousFragment(b ? currentFragment.getClass().getSimpleName() : currentFragment.getPreviousFragment());
					fragmentTransaction.addToBackStack(currentFragment.getClass().getSimpleName());
				}else{
					nextFragment.setPreviousFragment(null);
					fragmentTransaction.addToBackStack(null);
				}
				fragmentTransaction.commit();
			}
			nextFragment = null;
		}
	}

	private void clearBackStack() {
		FragmentManager fragmentManager = getSupportFragmentManager();

		if (0 < fragmentManager.getBackStackEntryCount()) {
			int id = fragmentManager.getBackStackEntryAt(0).getId();
			fragmentManager.popBackStackImmediate(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}

		List<Fragment> fragments = fragmentManager.getFragments();
		if (fragments == null) {
			return;
		}
		FragmentTransaction trans = fragmentManager.beginTransaction();
		for (Fragment fragment : fragments) {
			if (fragment != null) {
				trans.remove(fragment);
			}
		}
		trans.commit();

	}

	@Override
	public IStore getStore() {
		return null;
	}

	@Override
	public IToolbar getToolbar() {
		return toolbar;
	}

	@Override
	public void hideKeyboard() {

	}

	@Override
	public void back() {
		onBackPressed();
	}

	@Override
	public void clearProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showProgress() {

	}
}

package com.example.sven.lazyfragment.base;

import android.support.v4.app.Fragment;

/**
 * Created by Sven on 2016/11/30.
 */

public abstract class LazyFragment extends Fragment {

    protected boolean isVisble;
    public boolean isPrepared = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        loadData();
    }

    protected abstract void loadData();
}
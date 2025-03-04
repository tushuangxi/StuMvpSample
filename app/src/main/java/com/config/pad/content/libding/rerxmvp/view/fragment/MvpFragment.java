package com.config.pad.content.libding.rerxmvp.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.config.pad.content.libding.rerxmvp.base.BaseFragment;
import com.config.pad.content.libding.rerxmvp.base.BasePresenter;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}

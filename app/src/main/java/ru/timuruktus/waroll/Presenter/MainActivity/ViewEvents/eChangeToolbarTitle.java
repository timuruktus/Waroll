package ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents;

import android.app.Fragment;

public class EChangeToolbarTitle {

    /**
     * WHERE CATCHING:
     * waroll.Presenter.MainActivityPresenter {public void changeToolbarTitle}
     */

    public Fragment fragment;

    public EChangeToolbarTitle(Fragment fragment){
        this.fragment = fragment;
    }
}

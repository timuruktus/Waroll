package ru.timuruktus.waroll.Events;

import android.app.Fragment;

public class ChangeToolbarTitle {

    /**
     * WHERE CATCHING:
     * waroll.Presenter.MainActivityPresenter {public void changeToolbarTitle}
     */

    public Fragment fragment;

    public ChangeToolbarTitle(Fragment fragment){
        this.fragment = fragment;
    }
}

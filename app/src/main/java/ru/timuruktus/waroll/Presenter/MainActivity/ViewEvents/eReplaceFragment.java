package ru.timuruktus.waroll.Presenter.MainActivity.ViewEvents;

import android.app.Fragment;



public class EReplaceFragment {

    /**
     * WHERE CATCHING:
     * waroll.Presenter.MainActivityPresenter.java {public void changeToolbarTitle}
     * waroll.Presenter.MainActivityPresenter.java {public void replaceFragment}
     */

    public Fragment fragment;
    public boolean addToBackStack;

    public EReplaceFragment(Fragment fragment, boolean addToBackStack){
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }
}

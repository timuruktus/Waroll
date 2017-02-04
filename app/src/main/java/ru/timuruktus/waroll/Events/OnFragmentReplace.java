package ru.timuruktus.waroll.Events;

import android.app.Fragment;



public class OnFragmentReplace {

    /**
     * PRESENTERS:
     * waroll.Presenter.MainActivityPresenter.java {public void changeToolbarTitle}
     * waroll.Presenter.MainActivityPresenter.java {public void onFragmentReplace}
     */

    public Fragment fragment;
    public boolean addToBackStack;

    public OnFragmentReplace(Fragment fragment, boolean addToBackStack){
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }
}

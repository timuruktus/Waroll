package ru.timuruktus.waroll.Events;

import android.app.Fragment;

public class ChangeToolbarTitle {

    public Fragment fragment;

    public ChangeToolbarTitle(Fragment fragment){
        this.fragment = fragment;
    }
}

package ru.timuruktus.waroll.Presenter;


import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.waroll.View.MainActivity;

public class RegFragmentPresenter {


    private MainActivity mainActivity;

    public RegFragmentPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        //EventBus.getDefault().register(this);
    }
}

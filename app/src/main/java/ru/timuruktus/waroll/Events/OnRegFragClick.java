package ru.timuruktus.waroll.Events;

import java.util.HashMap;

public class OnRegFragClick {

    /**
     * WHERE CATCHING:
     * waroll.Presenter.RegFragmentPresenter.java {public void onRegFragClick}
     */

    public RegActions action;
    public HashMap<String, String> regData;

    public enum RegActions{
        REG
    }

    public OnRegFragClick(RegActions action){
        this.action = action;
    }

    public OnRegFragClick(RegActions action, HashMap<String, String> regData){
        this.action = action;
        this.regData = regData;
    }
}

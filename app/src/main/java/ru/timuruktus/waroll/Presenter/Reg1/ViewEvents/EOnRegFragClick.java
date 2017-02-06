package ru.timuruktus.waroll.Presenter.Reg1.ViewEvents;

import java.util.HashMap;

public class EOnRegFragClick {

    /**
     * WHERE CATCHING:
     * waroll.Presenter.RegFragmentPresenter.java {public void onRegFragClick}
     */

    public RegActions action;
    public HashMap<String, String> regData;

    public enum RegActions{
        REG,CHOOSE_PHOTO
    }

    public EOnRegFragClick(RegActions action){
        this.action = action;
    }

    public EOnRegFragClick(RegActions action, HashMap<String, String> regData){
        this.action = action;
        this.regData = regData;
    }
}

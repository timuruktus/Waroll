package ru.timuruktus.waroll.Events;


public class OnJoinFragClick {

    /**
     * USAGES:
     * JoinFragmentPresenter.java {public void onJoinFragClick}
     */

    public Actions actions;

    public enum Actions{
        REG,JOIN,PASS
    }

    public OnJoinFragClick(Actions actions){
        this.actions = actions;
    }

    public Actions getAction(){
        return actions;
    }
}

package ru.timuruktus.waroll.Events;


public class OnJoinFragClick {

    /**
     * WHERE CATCHING:
     * JoinFragmentPresenter.java {public void onJoinFragClick}
     */

    public JoinActions joinActions;

    public enum JoinActions {
        REG,JOIN,PASS
    }

    public OnJoinFragClick(JoinActions joinActions){
        this.joinActions = joinActions;
    }

    public JoinActions getAction(){
        return joinActions;
    }
}

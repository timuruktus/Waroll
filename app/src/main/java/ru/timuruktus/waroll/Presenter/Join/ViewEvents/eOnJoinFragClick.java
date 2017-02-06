package ru.timuruktus.waroll.Presenter.Join.ViewEvents;


public class EOnJoinFragClick {

    /**
     * WHERE CATCHING:
     * JoinFragmentPresenter.java {public void onJoinFragClick}
     */

    public JoinActions joinActions;

    public enum JoinActions {
        REG,JOIN,PASS
    }

    public EOnJoinFragClick(JoinActions joinActions){
        this.joinActions = joinActions;
    }

    public JoinActions getAction(){
        return joinActions;
    }
}

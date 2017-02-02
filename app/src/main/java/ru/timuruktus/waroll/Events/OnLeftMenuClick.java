package ru.timuruktus.waroll.Events;


public class OnLeftMenuClick {

    public MenuClick menuClick;

    public enum MenuClick{
        REGISTRATION
    }

    public OnLeftMenuClick(MenuClick menuClick){
        this.menuClick = menuClick;
    }

}

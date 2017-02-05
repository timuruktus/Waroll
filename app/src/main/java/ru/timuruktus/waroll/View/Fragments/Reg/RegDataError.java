package ru.timuruktus.waroll.View.Fragments.Reg;

public class RegDataError {

    public RegWrong regWrong;

    public enum RegWrong {
        PASS,EMAIL,LOGIN,EMPTY_FIELD,OTHER,LOGIN_EXISTS
    }

    public RegDataError(RegWrong regWrong){
        this.regWrong = regWrong;
    }

}

package ru.timuruktus.waroll.View.Fragments.Reg1;

public class RegDataError {

    public RegWrong regWrong;

    /**
     * What kind of problems may appear in
     * application, which can affect RegFragment
     */
    public enum RegWrong {
        PASS,EMAIL,EMPTY_FIELD,OTHER,LOGIN_EXISTS
    }

    public RegDataError(RegWrong regWrong){
        this.regWrong = regWrong;
    }

}

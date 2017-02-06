package ru.timuruktus.waroll.Model.Events;

import java.util.HashMap;

public class EPrepareRegister {

    public HashMap<String, String> regData;

    public EPrepareRegister(HashMap<String, String> regData){
        this.regData = regData;
    }
}

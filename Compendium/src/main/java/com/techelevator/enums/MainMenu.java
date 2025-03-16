package com.techelevator.enums;

public enum MainMenu {
    BESTIARY(1),
    CHARACTER_LOOKUP(2),
    SPELL_LIST(3),
    EXIT(4);


    private  final int value;
    private MainMenu(int value){
        this.value = value;
    }

    private int getValue(){
        return  this.value;
    }
}

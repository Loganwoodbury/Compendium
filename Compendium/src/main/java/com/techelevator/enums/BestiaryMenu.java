package com.techelevator.enums;

public enum BestiaryMenu {

    SEARCH_ALL(1),
    SEARCH_NAME(2),
    SEARCH_TYPE(3),
    RANDOM(4),
    ADD_NEW(5),
    UPDATE(6),
    DELETE(7),
    MAIN_MENU(8);


    private  final int value;
    private BestiaryMenu(int value){
        this.value = value;
    }

    private int getValue(){
        return  this.value;
    }
}

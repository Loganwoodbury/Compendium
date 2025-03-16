package com.techelevator.utilities;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.ByteArrayInputStream;

public class Console {
    public static final int DEFAULT_PADDING = 10;
    public static final String NEW_LINE = "\n";
    public static final String STAR = "*";
    public static final String DASH = "-";
    public static final String SPACE = " ";

    public static void bannerize(String bannerText){
        int bannerLength = bannerText.length() + DEFAULT_PADDING;

        String bannerOut = "";
        String padSpace = SPACE.repeat(DEFAULT_PADDING/2);

        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;
        bannerOut += padSpace + bannerText + padSpace + NEW_LINE;
        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;

        System.out.println(bannerOut);
    }

    public static void mainMenu(){
        int bannerLength = "2) Character Lookup".length() + DEFAULT_PADDING;
        String bannerOut = "";
        String padSpace = SPACE.repeat(DEFAULT_PADDING/2);

        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;
        bannerOut += padSpace + "1) Bestiary" + padSpace + NEW_LINE;
        bannerOut += padSpace + "2) Character Lookup" + padSpace + NEW_LINE;
        bannerOut += padSpace + "3) Spell List" + padSpace + NEW_LINE;
        bannerOut += padSpace + "4) Exit" + padSpace + NEW_LINE;
        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;

        System.out.println(bannerOut);

    }

    public static void bestiaryMenu(){
        int bannerLength = "2) Character Lookup".length() + DEFAULT_PADDING;
        String bannerOut = "";
        String padSpace = SPACE.repeat(DEFAULT_PADDING/2);

        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;
        bannerOut += padSpace + "1) Search all monsters" + padSpace + NEW_LINE;
        bannerOut += padSpace + "2) Search by Name" + padSpace + NEW_LINE;
        bannerOut += padSpace + "3) Search by type" + padSpace + NEW_LINE;
        bannerOut += padSpace + "4) Random monster" + padSpace + NEW_LINE;
        bannerOut += padSpace + "5) Add new monster" + padSpace + NEW_LINE;
        bannerOut += padSpace + "6) Update monster" + padSpace + NEW_LINE;
        bannerOut += padSpace + "7) Delete" + padSpace + NEW_LINE;
        bannerOut += padSpace + "8) Back to main menu" + padSpace + NEW_LINE;
        bannerOut += DASH.repeat(bannerLength) + NEW_LINE;

        System.out.println(bannerOut);
    }
}

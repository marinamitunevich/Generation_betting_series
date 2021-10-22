package com.lottery;


import com.lottery.menu.MainMenu;
import com.lottery.menu.Menu;

public class LotteryApplication {

    public static void main(String[] args) {

        Menu mainMenu = new MainMenu();
        mainMenu.showMenu();
    }
}

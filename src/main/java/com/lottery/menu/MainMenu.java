package com.lottery.menu;

import com.lottery.ApplicationContext;

import java.util.Scanner;

public class MainMenu extends BaseMenu{
    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Menu:");
        System.out.println("1 - choose game and generate numbers");
        System.out.println("2 - unlucky numbers");
        System.out.println("3 - exit");

        String readConsole = readerFromConsole.nextLine();

        Menu nextMenu = null;
        switch (readConsole) {
            case "1":
                nextMenu = new GameNamesMenu();
                break;
            case "2":
                nextMenu = new UnluckyNumbersMenu();
                break;
            case "3":
                exit();
                break;
            default:
                System.out.println("Incorrect data, enter please again: 1  2 or 3, try again");
                showMenu();
        }

        if (nextMenu != null) {
            nextMenu.showMenu();
            showMenu();
        }

    }
}

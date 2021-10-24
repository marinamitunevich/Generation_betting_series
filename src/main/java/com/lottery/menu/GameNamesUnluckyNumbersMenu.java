package com.lottery.menu;

import com.lottery.ApplicationContext;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GameNamesUnluckyNumbersMenu extends BaseMenu {

    private static Logger log = Logger.getLogger(MainMenu.class.getName());

    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Choose your lottery game to add or remove your unlucky numbers:");
        System.out.println("1 - Lotto");
        System.out.println("2 - EuroJackPot2From10");
        System.out.println("3 - EuroJackPot5From50");
        System.out.println("4 - back to Menu");
        System.out.println("5 - exit");

        String readConsole = readerFromConsole.nextLine();

        Menu nextMenu = null;
        switch (readConsole) {
            case "1":
                nextMenu = new UnluckyNumbersMenu(ApplicationContext.INSTANCE.getLottoLottery());
                break;
            case "2":
                nextMenu = new UnluckyNumbersMenu(ApplicationContext.INSTANCE.getEuroJackPot2From10());
                break;
            case "3":
                nextMenu = new UnluckyNumbersMenu(ApplicationContext.INSTANCE.getEuroJackPot5From50());
                break;
            case "4":
                ApplicationContext.INSTANCE.getRootMenu().showMenu();
                break;
            case "5":
                exit();
                break;
            default:
                System.out.println("Incorrect data, enter please again: 1  2 or 3, try again");
                log.error("Incorrect data, enter please again: 1  2 or 3, try again");
                showMenu();
        }

        if (nextMenu != null) {
            nextMenu.showMenu();
            showMenu();
        }
    }
}

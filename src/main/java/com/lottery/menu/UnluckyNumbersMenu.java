package com.lottery.menu;

import com.lottery.ApplicationContext;
import com.lottery.api.Lottery;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class UnluckyNumbersMenu extends BaseMenu {

    private static Logger log = Logger.getLogger(UnluckyNumbersMenu.class.getName());
    protected Lottery lottery;

    public UnluckyNumbersMenu(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Menu unlucky numbers:");
        System.out.println("1 - add your unlucky numbers");
        System.out.println("2 - delete your unlucky numbers");
        System.out.println("3 - back to Menu");
        System.out.println("4 - exit()");


        String readConsole = readerFromConsole.nextLine();

        switch (readConsole) {
            case "1":
                lottery.addUnluckyNumbers();
                break;
            case "2":
                lottery.removeUnluckyNumbers();
                break;
            case "3":
                ApplicationContext.INSTANCE.getRootMenu().showMenu();
                break;
            case "4":
                exit();
                break;
            default:
                System.out.println("Incorrect data, enter please again: 1 2 or 3");
                log.error("Incorrect data, enter please again: 1 2 or 3");
                showMenu();
        }
    }
}

package com.lottery.menu;

import com.lottery.ApplicationContext;
import com.lottery.api.Lottery;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class GameNamesMenu extends BaseMenu {

    private static Logger log = Logger.getLogger(GameNamesMenu.class.getName());

    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Choose your lottery game and generate numbers:");
        System.out.println("1 - Lotto");
        System.out.println("2 - Eurojackpot");
        System.out.println("3 - back to Menu");
        System.out.println("4 - exit");


        String readConsole = readerFromConsole.nextLine();
        Lottery lottery = null;

        switch (readConsole) {
            case "1":
                lottery = ApplicationContext.INSTANCE.getLottoLottery();
                break;
            case "2":
                lottery = ApplicationContext.INSTANCE.getEuroJackPotLottery();
                break;
            case "3":
                ApplicationContext.INSTANCE.getRootMenu().showMenu();
                break;
            case "4":
                exit();
                break;
            default:
                System.out.println("Incorrect data, enter please again: 1  2 or 3, try again");
                log.error("Incorrect data, enter please again: 1  2 or 3, try again");
                showMenu();
        }

        if (lottery != null) {
            lottery.generateNumbers();
        }
    }
}

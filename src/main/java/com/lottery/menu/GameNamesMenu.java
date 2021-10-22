package com.lottery.menu;

import com.lottery.ApplicationContext;
import com.lottery.api.Lottery;

import java.util.Scanner;

public class GameNamesMenu extends BaseMenu {

    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Choose your lottery game and generate numbers:");
        System.out.println("1 - Lotto");
        System.out.println("2 - Eurojackpot");
        System.out.println("3 - back to Menu");


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
                ApplicationContext.INSTANCE.getParentMenu().showMenu();
            default:
                System.out.println("Incorrect data, enter please again: 1  2 or 3, try again");
                showMenu();
        }

        if (lottery != null) {

            lottery.generateNumbers();
        }
    }
}

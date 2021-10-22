package com.lottery.menu;

import com.lottery.ApplicationContext;
import com.lottery.api.Lottery;

import java.util.Scanner;

public class UnluckyNumbersMenu extends BaseMenu {

    @Override
    public void showMenu() {

        Scanner readerFromConsole = ApplicationContext.INSTANCE.getReaderFromConsole();

        System.out.println("Menu unlucky numbers:");
        System.out.println("1 - add your unlucky numbers");
        System.out.println("2 - delete your unlucky numbers");
        System.out.println("3 - back to Menu");

        String readConsole = readerFromConsole.nextLine();

        switch (readConsole) {
            case "1":
                addUnluckyNumbers();
                break;
            case "2":
                deleteUnluckyNumbers();
                break;
            case "3":
                ApplicationContext.INSTANCE.getParentMenu().showMenu();
                break;
            default:
                System.out.println("Incorrect data, enter please again: 1 2 or 3");
                showMenu();
        }
    }

    private void deleteUnluckyNumbers() {

        Lottery lottery = null;

        lottery = ApplicationContext.INSTANCE.getLottoLottery();
        lottery.removeUnluckyNumbers();

        lottery = ApplicationContext.INSTANCE.getEuroJackPotLottery();
        lottery.removeUnluckyNumbers();
    }

    private void addUnluckyNumbers() {

        Lottery lottery = null;

        lottery = ApplicationContext.INSTANCE.getLottoLottery();
        lottery.addUnluckyNumbers();

        lottery = ApplicationContext.INSTANCE.getEuroJackPotLottery();
        lottery.addUnluckyNumbers();
    }
}

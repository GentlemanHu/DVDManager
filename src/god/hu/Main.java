package god.hu;

import god.hu.cli.ConsoleColors;
import god.hu.cli.LabPrinter;
import god.hu.model.DVD;
import god.hu.model.DVDManager;
import god.hu.model.ManagerMediator;
import god.hu.model.Time;
import god.hu.usage.DVDOperate;
import god.hu.usage.State;

import java.awt.*;
import java.io.Console;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final LabPrinter printer = new LabPrinter();
    private static final DVDManager manager = Lab.getLab().getManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManagerMediator mediator = new ManagerMediator(manager);

    public static void main(String[] args) throws Exception {
        try {
            run();
        } catch (Exception e) {
            System.out.println(ConsoleColors.GREEN+"ERROR!-异常-\n请联系管理员或重新尝试~"+ConsoleColors.RESET);
            exit();
        }
    }

    public static void init() {
        manager.addDVD(new DVD.Builder().setID(1).setState(State.ON_SHELF).setName("Sound of Nature").setTime(new Time.Builder().setBorrowTime(1).build()).build());
        manager.addDVD(new DVD.Builder().setID(2).setState(State.NOT_AVAI).setName("TokyoHot").setTime(new Time.Builder().setBorrowTime(2).build()).build());
        manager.addDVD(new DVD.Builder().setID(3).setState(State.ON_SHELF).setName("Java101").setTime(new Time.Builder().setBorrowTime(3).build()).build());
        printer.printHelp();
    }

    public static void run() {
        init();
        String tmp = "";
        int number = 0;
        for (; ; ) {
            tmp = scanner.next();
            if (isNumber(tmp) && tmp.length() == 1)
                number = Integer.parseInt(tmp);
            else if(tmp.equals("exit"))
                exit();
            else {
                System.out.println("-输入无效-\n输入\"0\"查看帮助");
                printer.printArrow();
                continue;
            }
            commandSelector(number);
        }
    }

    public static void msg(String msg) {
        System.out.println(msg);
    }

    public static boolean isInCMDList(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 9;
    }

    public static boolean isNumber(String s) {
        return s.matches("[0-9]+");
    }

    public static void exit(){
        System.out.println(ConsoleColors.YELLOW + "-退出系统-");
        System.out.println("GodLin's Manager 重置中...");
        System.out.println("Done!");
        System.exit(0);
    }

    public static void commandSelector(int number){
        if (!isInCMDList(number))
            number = 0;
        switch (number) {
            case 0:
                printer.printHelp();
                break;
            case 1:
                mediator.addDVD(scanner);
                printer.printTable();
                break;
            case 2:
                mediator.removeDVDById(scanner);
                printer.printTable();
                break;
            case 9:
                printer.printTable();
                break;
            default:
                printer.printHelp();
                break;
        }
    }
}

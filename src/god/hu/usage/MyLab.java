package god.hu.usage;

import god.hu.model.*;
import god.hu.usage.tool.cli.ConsoleColors;
import god.hu.usage.tool.cli.LabPrinter;
import god.hu.usage.abs.State;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyLab {
    private static final LabPrinter printer = new LabPrinter();
    private static final DVDManager manager = Lab.getLab().getManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManagerMediator mediator = new ManagerMediator(manager);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");


    public static void run() {
        for (; ; ) {
            System.out.println(ConsoleColors.PURPLE+"---请选择登录身份---\n---admin(以管理员身份登录)---\n---reader(以读者身份进入)---"+ConsoleColors.RESET);
            printer.printArrow();
            switch (Login.auth(scanner)) {
                case MANAGER:
                    System.out.println(ConsoleColors.RED + "login as manager" + ConsoleColors.RESET);
                    asManager();
                    break;
                case READER:
                    System.out.println(ConsoleColors.RED + "login as reader" + ConsoleColors.RESET);
                    break;
                case UNDEFINED:
                    System.out.println(ConsoleColors.RED + "not login " + ConsoleColors.RESET);
                    break;
                default:
                    break;
            }
        }
    }

    public static void asManager() {
        init();
        String tmp = "";
        int number = 0;
        for (; ; ) {
            tmp = scanner.next();
            if (isNumber(tmp) && tmp.length() == 1)
                number = Integer.parseInt(tmp);
            else if (tmp.equals("exit"))
                exit();
            else {
                System.out.println("-输入无效-\n输入\"0\"查看帮助");
                printer.printArrow();
                continue;
            }
            commandSelector(number);
        }
    }

    public static void init() {
        manager.addDVD(
                new DVD.Builder()
                        .setID(1)
                        .setState(State.ON_SHELF)
                        .setName("Sound of Nature")
                        .setTime(new Time.Builder()
                                .setBorrowTime(formatter.format(new Date()))
                                .build())
                        .build());
        manager.addDVD(
                new DVD.Builder()
                        .setID(2)
                        .setState(State.NOT_AVAI)
                        .setName("TokyoHot")
                        .setTime(new Time.Builder()
                                .setBorrowTime(formatter.format(new Date()))
                                .build())
                        .build());
        manager.addDVD(
                new DVD.Builder()
                        .setID(3)
                        .setState(State.ON_SHELF)
                        .setName("Java101")
                        .setTime(new Time.Builder()
                                .setBorrowTime(formatter.format(new Date()))
                                .build())
                        .build());
        printer.printHelp();
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

    public static void exit() {
        System.out.println(ConsoleColors.YELLOW + "-退出系统-");
        System.out.println("GodLin's Manager 重置中...");
        System.out.println("Done!");
        System.exit(0);
    }

    public static void commandSelector(int number) {
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

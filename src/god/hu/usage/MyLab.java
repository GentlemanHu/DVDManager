package god.hu.usage;

import god.hu.model.*;
import god.hu.usage.tool.cli.ConsoleColors;
import god.hu.usage.tool.cli.LabPrinter;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MyLab {
    private static final LabPrinter printer = new LabPrinter();
    private static final DVDManager manager = Lab.getManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManagerMediator mediator = new ManagerMediator(manager);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
    private static Reader reader = null;

    public static void init() {
        printer.printHelp();
    }

    public static void msg(String msg) {
        System.out.println(msg);
    }

    public static boolean isNumber(String s) {
        return s.matches("[0-9]+");
    }

    public static void run() {
        for (; ; ) {
            System.out.println(ConsoleColors.PURPLE + "---请选择登录身份---\n---admin(以管理员身份登录)---\n---reader(以读者身份进入)---\n---exit(退出)---" + ConsoleColors.RESET);
            printer.printArrow();
            switch (Login.auth(scanner)) {
                case MANAGER:
                    System.out.println(ConsoleColors.RED + "login as manager" + ConsoleColors.RESET);
                    asManager();
                    break;
                case READER:
                    System.out.println(ConsoleColors.RED + "login as reader" + ConsoleColors.RESET);
                    asReader();
                    break;
                case UNDEFINED:
                    System.out.println(ConsoleColors.RED + "not login\n请重新输入登录信息! " + ConsoleColors.RESET);
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
            else if (tmp.equals("logout")) {
                System.out.println(ConsoleColors.PURPLE + "已成功登出" + ConsoleColors.RESET);
                break;
            } else {
                System.out.println("-输入无效-\n输入\"0\"查看帮助");
                printer.printArrow();
                continue;
            }
            ManagerCommandSelector(number);
        }
    }

    public static void asReader() {
        printer.printReaderLogin();
        for (; ; ) {
            String tmp = "";
            int number = 0;
            tmp = scanner.next();
            if (isNumber(tmp) && tmp.length() == 1)
                number = Integer.parseInt(tmp);
            else if (tmp.equals("exit"))
                exit();
            else if (tmp.equals("logout")) {
                System.out.println(ConsoleColors.PURPLE + "已成功登出" + ConsoleColors.RESET);
                break;
            } else {
                System.out.println("-输入无效-\n输入\"0\"查看帮助");
                printer.printArrow();
                continue;
            }
            ReaderCommandSelector(number);
        }
    }

    public static void exit() {
        System.out.println(ConsoleColors.YELLOW + "-退出系统-");
        System.out.println("GodLin's Manager 退出中...");
        System.out.println("Done!");
        System.exit(0);
    }

    public static void ManagerCommandSelector(int number) {
        if (!isNumber(String.valueOf(number)))
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
                printer.printTable();
                mediator.removeDVDById(scanner);
                printer.printTable();
                break;
            case 3:
                mediator.addReader(scanner);
                printer.printReaderTable();
                break;
            case 4:
                printer.printReaderTable();
                mediator.removeReaderById(scanner);
                printer.printReaderTable();
                break;
            case 8:
                printer.printReaderTable();
                break;
            case 9:
                printer.printTable();
                break;
            default:
                printer.printHelp();
                break;
        }
    }

    public static void ReaderCommandSelector(int number) {
        if (!isNumber(String.valueOf(number)))
            number = 0;
        switch (number) {
            case 0:
                printer.printReaderLogin();
                break;
            case 1:
                printer.printReaderTable();
                break;
            case 2:
                reader = mediator.getReader(scanner);
                if (reader == null){
                    System.out.println("登录失败,请重试或联系管理员!");
                    printer.printArrow();
                }
                else {
                    readerLab();
                    printer.printReaderLogin();
                }
                break;
            default:
                printer.printReaderLogin();
                break;
        }
    }

    public static void readerLab() {
        printer.printReaderHelp(reader);
        for (; ; ) {
            String tmp = "";
            int number = 0;
            tmp = scanner.next();
            if (isNumber(tmp) && tmp.length() == 1)
                number = Integer.parseInt(tmp);
            else if (tmp.equals("exit"))
                exit();
            else if (tmp.equals("logout")) {
                System.out.println(ConsoleColors.PURPLE + "已成功登出" + ConsoleColors.RESET);
                break;
            } else {
                System.out.println("-输入无效-\n输入\"0\"查看帮助");
                printer.printArrow();
                continue;
            }
            realReaderSelector(number);
        }
    }

    public static void realReaderSelector(int number) {
        if (!isNumber(String.valueOf(number)))
            number = 0;
        switch (number) {
            case 0:
                printer.printReaderHelp(reader);
                break;
            case 1:
                //TODO: borrow
                System.out.println("borrow dvd");
                printer.printTable();
                mediator.borrowById(scanner,reader);
                break;
            case 2:
                //TODO: revert
                System.out.println("revert dvd");
                printer.printOwnList(reader);
                mediator.revertById(scanner,reader);
                break;
            case 3:
                System.out.println("renew dvd");
                System.out.println("Not implemented yet");
                //TODO: renew
            case 9:
                //TODO: fix time ,borrow time,revert time etc.
                printer.printOwnList(reader);
                break;
            default:
                printer.printReaderHelp(reader);
                break;
        }
    }
}

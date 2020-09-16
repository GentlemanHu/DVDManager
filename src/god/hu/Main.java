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

public class Main {
    private static final LabPrinter printer = new LabPrinter();
    private static final DVDManager manager = Lab.getLab().getManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManagerMediator mediator = new ManagerMediator(manager);

    public static void main(String[] args) throws Exception {
//        Console console = System.console();
//        if (console == null && !GraphicsEnvironment.isHeadless()) {
//            String filename = "D:\\own\\DVDManager\\out\\production\\DVDManager\\god\\hu\\Main.class";
//            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "cmd", "/k", "java  \"" + filename + "\""});
//        } else {
//            Main.main(new String[0]);
//            System.out.println("Program has ended, please type 'exit' to close the console");
//        }
        try {
            run();
        } catch (Exception e) {
            System.out.println(ConsoleColors.YELLOW + "-退出系统-");
            System.out.println("GodLin's Manager 重置中...");
            System.out.println("Done!");
        }
    }

    public static void init() {
        manager.addDVD(new DVD.Builder().setID(1).setState(State.ON_SHELF).setName("Wang2").setTime(new Time.Builder().setBorrowTime(1).build()).build());
        manager.addDVD(new DVD.Builder().setID(2).setState(State.ON_SHELF).setName("Wang3").setTime(new Time.Builder().setBorrowTime(2).build()).build());
        manager.addDVD(new DVD.Builder().setID(3).setState(State.ON_SHELF).setName("Wang4").setTime(new Time.Builder().setBorrowTime(3).build()).build());
        printer.printHelp();
    }

    public static void run() {
        int number;
        init();
        for (; ; ) {
            number = scanner.nextInt();
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

    public static void msg(String msg) {
        System.out.println(msg);
    }

    public static boolean isInCMDList(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 9;
    }
}

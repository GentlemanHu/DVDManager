package god.hu;

import god.hu.cli.LabPrinter;
import god.hu.model.DVD;
import god.hu.model.DVDManager;
import god.hu.model.Time;
import god.hu.usage.DVDOperate;
import god.hu.usage.State;

import java.util.Scanner;

public class Main {
    private static LabPrinter printer = new LabPrinter();
    private static DVDManager manager = Lab.getLab().getManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void init() {
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang2").setTime(new Time.Builder().setBorrowTime(1).build()).build());
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang3").setTime(new Time.Builder().setBorrowTime(2).build()).build());
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang4").setTime(new Time.Builder().setBorrowTime(3).build()).build());
        printer.printHelp();
    }

    public static void run() throws Exception {
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
                    printer.printTable();
                    break;
                case 2:
                    printer.printCurrent();
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
        return i == 0 || i == 1 || i == 2 || i == 3;
    }
}

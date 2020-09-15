package god.hu;

import god.hu.cli.LabPrinter;
import god.hu.model.DVD;
import god.hu.model.DVDManager;
import god.hu.model.Time;
import god.hu.usage.DVDOperate;
import god.hu.usage.State;

public class Main {
    public static void main(String[] args) throws Exception {
        LabPrinter printer = new LabPrinter();
        DVDManager manager = Lab.getLab().getManager();
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang2").setTime(new Time.Builder().setBorrowTime(1).build()).build());
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang3").setTime(new Time.Builder().setBorrowTime(2).build()).build());
        manager.addDVD(new DVD.Builder().setState(State.ON_SHELF).setName("Wang4").setTime(new Time.Builder().setBorrowTime(3).build()).build());
        printer.printTable();
    }

    public static void msg(String msg) {
        System.out.println(msg);
    }

}

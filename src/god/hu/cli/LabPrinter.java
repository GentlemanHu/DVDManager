package god.hu.cli;

import god.hu.Lab;
import god.hu.model.DVD;
import god.hu.model.DVDManager;
import god.hu.model.Time;
import god.hu.usage.State;
import god.hu.usage.TablePrint;

public class LabPrinter implements TablePrint {
    private Lab lab = Lab.getLab();
    private DVDManager manager;

    public LabPrinter(){
        manager = Lab.getManager();
        manager.addDVD(new DVD.Builder().setName("Nice").setState(State.ON_SHELF).setTime(new Time()).build());
        manager.addDVD(new DVD.Builder().setName("One").setState(State.ON_SHELF).setTime(new Time()).build());
        manager.addDVD(new DVD.Builder().setName("Two").setState(State.ON_SHELF).setTime(new Time()).build());
        manager.addDVD(new DVD.Builder().setName("Three").setState(State.ON_SHELF).setTime(new Time()).build());
    }

    @Override
    public void printTable() {
        TableRender render = new TableRender();
        render.setShowVerticalLines(true);
        render.setHeaders("ID","NAME","TIME");
        manager.getDvds().stream().forEach((x)->render.addRow(x.getName(),x.getState().toString(),x.getTime().toString()));
        render.print();
    }

    @Override
    public void printCurrent() {

    }
}

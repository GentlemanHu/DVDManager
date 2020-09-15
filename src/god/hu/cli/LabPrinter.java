package god.hu.cli;

import god.hu.Lab;
import god.hu.model.DVD;
import god.hu.model.DVDManager;
import god.hu.model.Time;
import god.hu.usage.State;
import god.hu.usage.TablePrint;

public class LabPrinter implements TablePrint {
    private Lab lab;
    private DVDManager manager;

    public LabPrinter(){
        this.lab = Lab.getLab();
        manager = Lab.getLab().getManager();
    }

    @Override
    public void printTable() {
        TableRender render = new TableRender();
        render.setShowVerticalLines(true);
        render.setHeaders("STATE","NAME","TIME");
        manager.getDvds().stream().forEach((x)->render.addRow(x.getName(),x.getState().toString(),x.getTime().getBorrowTime().toString()));
        render.print();
    }

    @Override
    public void printCurrent() {

    }
}

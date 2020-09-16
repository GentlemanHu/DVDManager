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
        render.setHeaders("ID","NAME","STATE","TIME");
        manager.getDvds().forEach((x)->render.addRow(x.getId().toString(),x.getName(),x.getState().toString(),x.getTime().getBorrowTime().toString()+"d"));
        render.print();
        printArrow();
    }

    @Override
    public void printCurrent() {
        printTable();
        printArrow();
    }

    public void printHelp(){
        System.out.println(ConsoleColors.PURPLE+"---GodLin's Manager---"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE+"Help:\nSelect Number to input.\n0: Print Help Message.\n1: Add DVD to lab\n2: Delete DVD from lab\n3: Not Implemented yet!\n9: Print Lab table\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令："+ConsoleColors.RESET);
        printArrow();
    }

    public void printArrow(){
        System.out.print(ConsoleColors.RED+">>> "+ConsoleColors.RESET);
    }
}

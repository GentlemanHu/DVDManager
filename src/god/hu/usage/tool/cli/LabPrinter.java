package god.hu.usage.tool.cli;

import god.hu.db.MDBOperator;
import god.hu.model.Lab;
import god.hu.model.DVDManager;
import god.hu.usage.abs.TablePrint;

public class LabPrinter implements TablePrint {
    private Lab lab;
    private DVDManager manager;
    private MDBOperator operator;

    public LabPrinter(){
        this.lab = Lab.getLab();
        operator = new MDBOperator();
        manager = Lab.getManager();
    }

    @Override
    public void printTable() {
        TableRender render = new TableRender();
        try {
            operator.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        render.setShowVerticalLines(true);
        render.setHeaders("ID","TIME_SERIAL","STATE","NAME");
        manager.getDvds().forEach((x)->render.addRow(x.getId().toString(),x.getTime().getSerial(),x.getState().toString(),x.getName()));
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
        System.out.println(ConsoleColors.BLUE+"Help:\nSelect Number to input.\n0: Print Help Message.\n1: Add DVD to lab\n2: Delete DVD from lab\n3: Not Implemented yet!\n9: Print Lab table\nlogout: 退出登录\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令："+ConsoleColors.RESET);
        printArrow();
    }

    public void printHelp2(){
        System.out.println(ConsoleColors.PURPLE+"---GodLin's Lab---"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE+"Help:\nSelect Number to input.\n0: Print Help Message.\n1: Borrow DVD \n2: Revert DVD\n3: Renew DVD \n9: Print Own DVD List\nlogout: 退出登录\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令："+ConsoleColors.RESET);
        printArrow();
    }

    public void printArrow(){
        System.out.print(ConsoleColors.RED+">>> "+ConsoleColors.RESET);
    }
}

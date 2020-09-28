package god.hu.usage.tool.cli;

import god.hu.db.MDBOperator;
import god.hu.model.Lab;
import god.hu.model.DVDManager;
import god.hu.model.Reader;
import god.hu.usage.abs.TablePrint;

import java.util.ArrayList;

public class LabPrinter implements TablePrint {
    private Lab lab;
    private DVDManager manager;
    private MDBOperator operator;

    public LabPrinter() {
        this.lab = Lab.getLab();
        operator = new MDBOperator();
        manager = Lab.getManager();
    }

    @Override
    public void printCurrent() {
        printTable();
        printArrow();
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
        render.setHeaders("ID", "TIME_SERIAL", "STATE", "NAME");
        manager.getDvds().forEach((x) -> render.addRow(x.getId().toString(), x.getTime().getSerial(), x.getState().toString(), x.getName()));
        render.print();
        printArrow();
    }

    @Override
    public void printReaderTable() {
        TableRender render = new TableRender();
        ArrayList<Reader> readers = null;
        try {
            readers = operator.getReaders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        render.setShowVerticalLines(true);
        render.setHeaders("NAME", "ID", "DVD_LIST_ID");
        assert readers != null;
        readers.forEach(reader -> render.addRow(reader.getName(), reader.getId().toString(), reader.getDvd_list_id().toString()));
        render.print();
        printArrow();
    }

    @Override
    public void printOwnList(Reader reader) {
        TableRender render = new TableRender();

        try {
            operator.getAllReaderOwnDVDListByReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败,请重试或联系管理员!");
        }
        //TODO: select time message!
        render.setShowVerticalLines(true);
        render.setHeaders("ID", "NAME");
        reader.getOwn().forEach(x -> {
            if (x != null)
                render.addRow(x.getId().toString(), x.getName());
        });
        render.print();
        printArrow();
    }

    public void printHelp() {
        System.out.println(ConsoleColors.PURPLE + "---GodLin's Manager---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Help:\nSelect Number to input.\n0: Print Help Message.\n1: Add DVD to lab\n2: Delete DVD from lab\n3: Add new reader\n4: Remove Reader By ID\n8: Print all reader\n9: Print Lab table\nlogout: 退出登录\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令：" + ConsoleColors.RESET);
        printArrow();
    }

    public void printReaderLogin() {
        System.out.println(ConsoleColors.PURPLE + "---GodLin's Lab---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Help:\nSelect Number to input.\n0: Print Help Message.\n1: Print Reader List\n2: Login with Reader ID\n3: Not implemented YET!\nlogout: 退出到上一层\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令：" + ConsoleColors.RESET);
        printArrow();
    }

    public void printReaderHelp(Reader reader) {
        System.out.println("---" + ConsoleColors.PURPLE + reader.getName() + ConsoleColors.RESET + "'s Lab---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Help:\nSelect Number to input.\n0: Print Help Message.\n1: Borrow DVD \n2: Revert DVD\n3: Renew DVD\n8: Print all reader list\n9: Print Own DVD List\nlogout: 退出登录\nexit: Exit System,退出系统\n 选择`一个`数字来执行指令：" + ConsoleColors.RESET);
        printArrow();
    }

    public void printArrow() {
        System.out.print(ConsoleColors.RED + ">>> " + ConsoleColors.RESET);
    }
}

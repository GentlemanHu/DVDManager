package god.hu.model;

import god.hu.usage.MyLab;
import god.hu.usage.tool.SerialNumberGenerator;
import god.hu.usage.tool.cli.ConsoleColors;
import god.hu.usage.abs.DVDMediatorOperate;
import god.hu.usage.abs.State;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ManagerMediator implements DVDMediatorOperate {
    private DVDManager manager;
    private SerialNumberGenerator generator = new SerialNumberGenerator(7);

    public ManagerMediator(DVDManager manager) {
        this.manager = manager;
    }


    @Override
    public DVD borrow(int id, Reader reader) throws Exception {
        return manager.borrow(id, reader);
    }

    @Override
    public DVD revert(int id, Reader reader) throws Exception {
        return manager.revert(id, reader);
    }

    @Override
    public DVD revert(int id) {
        return null;
    }

    @Override
    public void removeDVDById(Integer id) throws Exception {
        manager.removeDVDById(id);
    }

    @Override
    public DVD getDVDById(Integer id) throws Exception {
        return manager.getDVDById(id);
    }

    @Override
    public void removeReaderById(Integer id) throws Exception {
        manager.removeReaderById(id);
    }

    public void removeDVDById(Scanner sc) {
        System.out.println("请输入要删除的DVD id(如,1,2..)");
        boolean numeric = true;
        int id;
        printArrow();
        for (; ; ) {
            String s = sc.next();
            numeric = s.matches("-?\\d+(\\.\\d+)?");
            if (numeric) {
                id = Integer.parseInt(s);
                break;
            } else {
                System.out.println(ConsoleColors.RED + "id无效,请重新输入或联系管理员!" + ConsoleColors.RESET);
                return;
            }
        }
        try {
            removeDVDById(id);
        } catch (Exception e) {
            System.out.println(ConsoleColors.YELLOW + "失败!指定的id不存在或不正确,请检查!" + ConsoleColors.RESET);
        }
    }

    public void addDVD(Scanner sc) {
        DVD dvd = new DVD();
        dvd.setId(generator.getNumber());
        System.out.println("请输入DVD名称:(如:黄家驹全集,不要有空格)");
        printArrow();
        dvd.setName(sc.next());
        System.out.println("请输入状态:(0为在库,1为不可借)\n只能(0,1)");
        printArrow();
        dvd.setState(sc.nextInt() == 0 ? State.ON_SHELF : State.NOT_AVAI);
        dvd.setTime(new Time.Builder().setSerial(generator.generate()).setId(generator.getNumber()).build());
        manager.addDVD(dvd);
    }


    public void addReader(Scanner sc) {
        Reader reader = new Reader();
        String name = "";
        reader.setId(generator.getNumber());
        System.out.println("请输入Reader名字(不要有空格):");
        printArrow();
        for (; ; ) {
            name = sc.next();
            if (!name.equals("null"))
                break;
        }
        reader.setName(name);
        reader.setDvd_list_id(generator.getNumber());
        manager.addReader(reader);
    }

    public void removeReaderById(Scanner sc) {
        System.out.println("请输入要删除的Reader ID:");
        boolean numeric = true;
        int id;
        printArrow();
        for (; ; ) {
            String s = sc.next();
            numeric = s.matches("-?\\d+(\\.\\d+)?");
            if (numeric) {
                id = Integer.parseInt(s);
                break;
            } else {
                System.out.println(ConsoleColors.RED + "id无效,请重新输入或联系管理员!" + ConsoleColors.RESET);
                return;
            }
        }
        try {
            removeReaderById(id);
        } catch (Exception e) {
            System.out.println(ConsoleColors.YELLOW + "失败!指定的id不存在或不正确,请检查!" + ConsoleColors.RESET);
        }
    }

    public Reader getReader(Scanner sc) {
        Reader reader = null;
        String id;
        for (; ; ) {
            System.out.println("请输入Reader ID登录:");
            printArrow();
            id = sc.next();
            if (!id.equals("0") && MyLab.isNumber(id))
                break;
            else System.out.println("请检查ID是否正确,再重试!");
        }
        try {
            reader = manager.getReader(Integer.parseInt(id));
            if (reader == null)
                throw new Exception();
        } catch (Exception e) {
            System.out.println("失败,请重试,或者联系管理员!");
            return reader;
        }
        return reader;
    }

    public DVD borrowById(Scanner sc, Reader reader) {
        DVD dvd = null;
        int id;
        System.out.println(ConsoleColors.GREEN + "请输入借阅DVD的id:" + ConsoleColors.RESET);
        printArrow();
        id = sc.nextInt();
        try {
            dvd = manager.borrow(id, reader);
        } catch (Exception e) {
            System.out.println("借阅失败,请重试或联系管理员!");
            System.out.println("请检查自己列表是否已满!");
        }
        System.out.println(ConsoleColors.BLUE + "借阅成功!" + ConsoleColors.RESET);
        printArrow();
        return dvd;
    }

    public DVD revertById(Scanner sc, Reader reader) {
        DVD dvd = null;
        int id;
        System.out.println(ConsoleColors.GREEN + "请输入归还DVD的id:" + ConsoleColors.RESET);
        printArrow();
        id = sc.nextInt();
        try {
            dvd = manager.revert(id, reader);
        } catch (Exception e) {
            System.out.println("归还失败,请重试或联系管理员!");
            System.out.println("请检查id是否正确!");
        }
        System.out.println(ConsoleColors.BLUE + "归还成功!" + ConsoleColors.RESET);
        printArrow();
        return dvd;
    }

    public void printArrow() {
        System.out.print(ConsoleColors.RED + ">>> " + ConsoleColors.RESET);
    }

    public ArrayList<Reader> getAllReader() throws SQLException {
        return manager.getReaders();
    }
}

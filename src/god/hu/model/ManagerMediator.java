package god.hu.model;

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
                printArrow();
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
        System.out.println("请输入DVD名称:(如:黄家驹全集)");
        printArrow();
        dvd.setName(sc.next());
        System.out.println("请输入状态:(0为在库,1为不可借)");
        printArrow();
        dvd.setState(sc.nextInt() == 0 ? State.ON_SHELF : State.NOT_AVAI);
        printArrow();
        dvd.setTime(new Time.Builder().setSerial(generator.generate()).setId(generator.getNumber()).build());
        System.out.println(dvd.toString());
        manager.addDVD(dvd);
    }


    public void addReader(Scanner sc) {
        Reader reader = new Reader();
        reader.setId(generator.getNumber());
        System.out.println("请输入Reader名字:");
        reader.setDvd_list_id(generator.getNumber());
        printArrow();
        System.out.println(reader.toString());
        manager.addReader(reader);
    }

    public Reader getReader(Scanner sc) {
        Reader reader = null;
        int id;
        for (; ; ) {
            System.out.println("请输入Reader ID登录:");
            printArrow();
            id = sc.nextInt();
            if (id != 0)
                break;
        }
        try {
            reader = manager.getReader(id);
            if (reader == null)
                throw new Exception();
        } catch (Exception e) {
            System.out.println("失败,请重试,或者联系管理员!");
            return reader;
        }
        return reader;
    }

    public void printArrow() {
        System.out.print(ConsoleColors.RED + ">>> " + ConsoleColors.RESET);
    }

    public ArrayList<Reader> getAllReader() throws SQLException {
        return manager.getReaders();
    }
}

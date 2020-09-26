package god.hu.model;

import god.hu.usage.tool.SerialNumberGenerator;
import god.hu.usage.tool.cli.ConsoleColors;
import god.hu.usage.abs.DVDMediatorOperate;
import god.hu.usage.abs.State;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ManagerMediator implements DVDMediatorOperate {
    private DVDManager manager;
    private SerialNumberGenerator generator=new SerialNumberGenerator(7);
    public ManagerMediator(DVDManager manager) {
        this.manager = manager;
    }


    @Override
    public DVD revert(int id) {
        return null;
    }

    @Override
    public void removeDVDById(Integer id) {
        manager.removeDVDById(id);
    }

    public void removeDVDById(Scanner sc) {
        System.out.println("请输入要删除的DVD id(如,1,2..)");
        printArrow();
        int id = sc.nextInt();
        try {
            removeDVDById(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ConsoleColors.YELLOW + "指定的id不存在或不正确,请检查!" + ConsoleColors.RESET);
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

    public void removeDVD(Integer id) {

    }

    @Override
    public void renew(int id, Time time) {

    }

    public void printArrow() {
        System.out.print(ConsoleColors.RED + ">>> " + ConsoleColors.RESET);
    }
}

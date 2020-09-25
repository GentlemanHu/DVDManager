package god.hu.model;

import god.hu.usage.abs.ReaderOperate;

import java.util.ArrayList;

public class Reader implements ReaderOperate {
    private String name;
    private Integer id;
    private ArrayList<DVD> own;
    private DVDManager manager;

    public Reader() {
    }

    public Reader(DVDManager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DVD> getOwn() {
        return own;
    }

    public void setOwn(ArrayList<DVD> own) {
        this.own = own;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public DVD borrow(int id) {
        return manager.borrow(id);
    }

    @Override
    public DVD revert(int id) {
        return manager.revert(id);
    }

    @Override
    public void renew(int id, Time time) {
        manager.renew(id, time);
    }
}

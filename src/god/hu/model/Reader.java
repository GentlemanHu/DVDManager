package god.hu.model;

import god.hu.usage.ReaderOperate;

public class Reader implements ReaderOperate {
    private String name;
    private OwnList<DVD> own;
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

    public OwnList<DVD> getOwn() {
        return own;
    }

    public void setOwn(OwnList<DVD> own) {
        this.own = own;
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

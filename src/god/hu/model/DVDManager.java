package god.hu.model;

import god.hu.usage.DVDOperate;
import god.hu.usage.State;

import java.util.ArrayList;

public class DVDManager implements DVDOperate {
    public OwnList<DVD> getDvds() {
        return dvds;
    }

    public void setDvds(OwnList<DVD> dvds) {
        this.dvds = dvds;
    }

    public OwnList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(OwnList<Reader> readers) {
        this.readers = readers;
    }

    public DVD getBorrow() {
        return borrow;
    }

    public void setBorrow(DVD borrow) {
        this.borrow = borrow;
    }

    public DVD getRevert() {
        return revert;
    }

    public void setRevert(DVD revert) {
        this.revert = revert;
    }

    public DVDManager() {
        dvds = new OwnList<DVD>();
        readers = new OwnList<Reader>();
    }

    @Override
    public void addReader(Reader reader) {
        readers.add(reader);
    }

    @Override
    public Reader getReader(int id) {
        return readers.get(id);
    }

    @Override
    public DVD borrow(int id) {
        ((DVD)dvds.get(id)).setState(State.NOT_AVAI);
        return (DVD)dvds.get(id);
    }

    @Override
    public DVD revert(int id) {
        ((DVD)dvds.get(id)).setState(State.ON_SHELF);
        return (DVD)dvds.get(id);
    }

    @Override
    public void addDVD(DVD dvd) {
        dvds.add(dvd);
    }

    @Override
    public void removeDVD(DVD dvd) {
        dvd.setState(State.NOT_AVAI);
        dvds.remove(dvd);
    }

    @Override
    public void removeDVDById(Integer id) {
        if (id > dvds.get(dvds.size()-1).getId())
            throw new IndexOutOfBoundsException();
        dvds.remove(id-1);
    }

    @Override
    public void renew(int id, Time time) {
        dvds.get(id).setTime(time);
    }

    private volatile OwnList<DVD> dvds;
    private OwnList<Reader> readers;
    private DVD borrow, revert;
}

package god.hu.model;

import god.hu.usage.abs.DVDOperate;
import god.hu.usage.abs.State;

import java.util.ArrayList;

public class DVDManager implements DVDOperate {
    public ArrayList<DVD> getDvds() {
        return dvds;
    }

    public void setDvds(ArrayList<DVD> dvds) {
        this.dvds = dvds;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
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
        dvds = new ArrayList<DVD>();
        readers = new ArrayList<Reader>();
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
        dvds.get(id).setState(State.NOT_AVAI);
        return dvds.get(id);
    }

    @Override
    public DVD revert(int id) {
        dvds.get(id).setState(State.ON_SHELF);
        return dvds.get(id);
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

    private volatile ArrayList<DVD> dvds;
    private ArrayList<Reader> readers;
    private DVD borrow, revert;
}

package god.hu.model;

import god.hu.db.MDBOperator;
import god.hu.usage.abs.DVDOperate;
import god.hu.usage.abs.State;

import java.sql.SQLException;
import java.util.ArrayList;

public class DVDManager implements DVDOperate {
    private final MDBOperator operator;
    private volatile ArrayList<DVD> dvds;
    private ArrayList<Reader> readers;
    private DVD borrow, revert;

    public DVDManager() {
        dvds = new ArrayList<DVD>();
        readers = new ArrayList<Reader>();
        operator = new MDBOperator();
    }

    public ArrayList<DVD> getDvds() {
        return dvds;
    }

    public void setDvds(ArrayList<DVD> dvds) {
        this.dvds = dvds;
    }

    public ArrayList<Reader> getReaders() throws SQLException {
        return operator.getReaders();
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

    @Override
    public void addReader(Reader reader) {
        operator.addReader(reader);
    }

    @Override
    public Reader getReader(int id) {
        return operator.getReader(id);
    }

    @Override
    public DVD borrow(int id) {
        return operator.borrow(id);
    }

    @Override
    public DVD borrow(int id, Reader reader) throws Exception {
        return operator.borrow(id, reader);
    }

    @Override
    public DVD revert(int id, Reader reader) throws Exception {
        return operator.revert(id, reader);
    }

    @Override
    public DVD revert(int id) {
        return operator.revert(id);
    }

    @Override
    public void addDVD(DVD dvd) {
        dvds.add(dvd);
        operator.addDVD(dvd);
    }

    @Override
    public void removeDVD(DVD dvd) {
        dvd.setState(State.NOT_AVAI);
        dvds.remove(dvd);
    }

    @Override
    public void removeDVDById(Integer id) throws Exception {
        operator.removeDVDById(id);
    }

    @Override
    public DVD getDVDById(Integer id) throws Exception {
        return operator.getDVDById(id);
    }

    @Override
    public void removeReaderById(Integer id) throws Exception {
        operator.removeReaderById(id);
    }

    @Override
    public void renew(int id, Time time) {
        dvds.get(id).setTime(time);
    }
}

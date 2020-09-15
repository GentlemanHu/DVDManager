package god.hu.model;

import god.hu.usage.DVDOperate;
import god.hu.usage.State;

import java.util.ArrayList;

public class DVDManager implements DVDOperate {
  private ArrayList<DVD> dvds;
  private ArrayList<Reader> readers;
  private DVD borrow,revert;

  public DVDManager(){
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
  public void renew(int id,Time time) {
    dvds.get(id).setTime(time);
  }
}

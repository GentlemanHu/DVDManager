package god.hu.usage;

import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;

public interface DVDMediatorOperate extends DVDOperate{

    @Override
    default void addReader(Reader reader) {

    }

    @Override
    default Reader getReader(int id) {
        return null;
    }

    @Override
    default DVD borrow(int id) {
        return null;
    }

    @Override
    default DVD revert(int id) {
        return null;
    }

    @Override
    default void addDVD(DVD dvd) {

    }

    @Override
    default void removeDVD(DVD dvd) {

    }

    @Override
    default void renew(int id, Time time) {

    }
}

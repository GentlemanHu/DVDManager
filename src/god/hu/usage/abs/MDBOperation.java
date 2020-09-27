package god.hu.usage.abs;

import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;

import java.util.ArrayList;

public interface MDBOperation extends ReaderOperate,DVDOperate{
    Integer selectCountByName(String name) throws Exception;
    ArrayList<DVD> selectAll() throws Exception;
    ArrayList<String> selectDistinctName() throws Exception;
    void updateTimeBySerial(String serial,Time time) throws Exception;
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
    default void renew(int id, Time time) {

    }
}

package god.hu.usage.abs;

import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;

import java.util.ArrayList;

public interface MDBOperation extends ReaderOperate, DVDOperate {
    Integer selectCountByName(String name) throws Exception;

    ArrayList<DVD> selectAll() throws Exception;

    ArrayList<String> selectDistinctName() throws Exception;

    void updateTimeBySerial(String serial, Time time) throws Exception;

    void updateReaderListByReaderId(Integer reader_id, DVD dvd) throws Exception;

    void removeDVDFromReaderListByReaderId(Integer reader_id, DVD dvd) throws Exception;

    Integer findEmptyListInReaderListByReaderId(Integer reader_id) throws Exception;

    Integer findMatchFromReaderList(Integer reader_id,Integer dvd_id) throws Exception;

    void insertNewReaderListByIdFromReader(Integer id) throws Exception;

    void insertNewTimeWhenAddDVD(Integer id, String serial) throws Exception;

    ArrayList<DVD> getAllReaderOwnDVDListByReader(Reader reader) throws Exception;

    @Override
    default void addReader(Reader reader) {

    }

    @Override
    default Reader getReader(int id) {
        return null;
    }

    DVD borrow(int id, Reader reader) throws Exception;

    @Override
    default DVD revert(int id) {
        return null;
    }

    @Override
    default void renew(int id, Time time) {

    }
}

package god.hu.usage;

import god.hu.model.DVD;
import god.hu.model.Reader;
import god.hu.model.Time;

public interface DVDOperate {
    void addReader(Reader reader);

    Reader getReader(int id);

    DVD borrow(int id);

    DVD revert(int id);

    void addDVD(DVD dvd);

    void removeDVD(DVD dvd);

    void removeDVDById(Integer id);

    void renew(int id,Time time);
}

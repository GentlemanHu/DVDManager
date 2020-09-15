package usage;

import model.DVD;
import model.Reader;
import model.Time;

public interface DVDOperate {
    void addReader(Reader reader);

    Reader getReader(int id);

    DVD borrow(int id);

    DVD revert(int id);

    void addDVD(DVD dvd);

    void removeDVD(DVD dvd);

    void renew(int id,Time time);
}

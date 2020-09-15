package usage;

import model.DVD;
import model.Time;

public interface ReaderOperate {
    DVD borrow(int id);

    DVD revert(int id);

    void renew(int id, Time time);
}

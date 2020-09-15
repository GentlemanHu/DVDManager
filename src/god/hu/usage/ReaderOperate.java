package god.hu.usage;

import god.hu.model.DVD;
import god.hu.model.Time;

public interface ReaderOperate {
    DVD borrow(int id);

    DVD revert(int id);

    void renew(int id, Time time);
}

package god.hu.usage.abs;

import god.hu.model.DVD;

import java.util.ArrayList;

public interface DBOperation {
    Integer selectCountByName(String name) throws Exception;
    ArrayList<DVD> selectAll() throws Exception;
    ArrayList<String> selectDistinctName() throws Exception;
}

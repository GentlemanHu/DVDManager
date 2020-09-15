package god.hu;

import god.hu.cli.TableRender;
import god.hu.model.DVDManager;
import god.hu.usage.DVDOperate;

public class Lab {
    private static final Lab lab = new Lab();
    private static final DVDOperate dealer= new DVDManager();
    private TableRender render = new TableRender();
    private Lab(){}

    public static Lab getLab(){
        synchronized (Lab.class){
            return lab;
        }
    }

    public DVDOperate getDealer(){
        return dealer;
    }

}

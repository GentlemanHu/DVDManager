package god.hu;

import god.hu.cli.TableRender;
import god.hu.model.DVDManager;
import god.hu.usage.DVDOperate;

public class Lab {
    private static final Lab lab = new Lab();
    private static final DVDOperate dealer = new DVDManager();
    private static final DVDManager manager = new DVDManager();

    private Lab() {
    }

    public static Lab getLab() {
        synchronized (Lab.class) {
            return lab;
        }
    }

    public static DVDManager getManager() {
        return manager;
    }

    public static DVDOperate getDealer() {
        return dealer;
    }


}

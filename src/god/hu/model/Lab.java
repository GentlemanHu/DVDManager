package god.hu.model;

import god.hu.usage.abs.DVDOperate;

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

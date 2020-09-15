import model.DVDManager;
import usage.DVDOperate;

public class Lab {
    private static final Lab lab = new Lab();
    private static final DVDOperate dealer= new DVDManager();

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

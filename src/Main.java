import model.DVD;
import model.Reader;
import usage.DVDOperate;
import usage.State;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello --->@" + System.currentTimeMillis());

        Lab lab = Lab.getLab();
        DVDOperate dealer = lab.getDealer();

        dealer.addDVD(new DVD.Builder().setName("okk").setState(State.ON_SHELF).build());

        System.out.println(dealer.borrow(0).getName());
    }


    public static void msg(String msg) {
        System.out.println(msg);
    }

}

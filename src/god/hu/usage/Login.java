package god.hu.usage;

import god.hu.usage.abs.Identity;

import java.util.Scanner;

public class Login {
    public Login() {
    }

    public static Identity auth(Scanner sc) {
        if (sc.next().equals("admin"))
            return Identity.MANAGER;
        if (sc.next().equals("reader"))
            return Identity.READER;
        else return Identity.UNDEFINED;
    }
}

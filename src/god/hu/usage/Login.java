package god.hu.usage;

import god.hu.usage.abs.Identity;

import java.util.Scanner;

public class Login {
    public Login() {
    }

    public static Identity auth(Scanner sc) {
        switch (sc.next()){
            case "admin":
                return Identity.MANAGER;
            case "reader":
                return Identity.READER;
            case "exit":
                MyLab.exit();
            default:
                return Identity.UNDEFINED;
        }
    }
}

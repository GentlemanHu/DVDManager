package god.hu;

import god.hu.usage.MyLab;
import god.hu.usage.tool.cli.ConsoleColors;

public class Main {
    public static volatile String ip = "", name = "", pwd = "";

    public static void main(String[] args) {
        try {
            argsConfig(args);
            MyLab.run();
        } catch (Exception e) {
            System.out.println(ConsoleColors.GREEN + "ERROR!-异常-\n请联系管理员或重新尝试~" + ConsoleColors.RESET);
            MyLab.exit();
        }
    }

    public static void argsConfig(String[] args) {
        switch (args.length) {
            case 2:
                configIP(args);
                break;
            case 4:
                configIP(args);
                configName(args);
                break;
            case 6:
                configIP(args);
                configName(args);
                configPwd(args);
                break;
            default:
                System.out.println(ConsoleColors.RED + "当前使用默认db 地址以及默认用户名密码:" + "192.168.113.104" + "\n可使用\"-ip 123.123.123.123\"类似参数定义db地址\n\"-user name -pwd password\"" + ConsoleColors.RESET);
                break;
        }
    }

    public static void configIP(String[] args) {
        if (args[0].equals("-ip")) {
            ip = args[1];
            System.out.println(ConsoleColors.RED + "当前自定义IP为:" + args[1] + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "参数不正确,请检查!格式\"-ip 123.123.123.123\"" + ConsoleColors.RESET);
        }
    }

    public static void configName(String[] args) {
        if (args[2].equals("-name")) {
            name = args[3];
            System.out.println(ConsoleColors.RED + "当前自定义name为:" + args[3] + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "参数不正确,请检查!格式\"-name name\"" + ConsoleColors.RESET);
        }
    }

    public static void configPwd(String[] args) {
        if (args[4].equals("-pwd")) {
            pwd = args[5];
            System.out.println(ConsoleColors.RED + "当前自定义password为:" + args[5] + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "参数不正确,请检查!格式\"-pwd password\"" + ConsoleColors.RESET);

        }
    }
}

package god.hu;

import god.hu.db.MDBOperator;
import god.hu.usage.MyLab;
import god.hu.usage.tool.cli.ConsoleColors;

public class Main extends MyLab {
    public static void main(String[] args) {
        if (args.length == 2) {
            if (args[0].equals("-ip")) {
                MDBOperator.ip = args[1];
            }
        } else {
            System.out.println("当前使用默认db 地址:" + MDBOperator.ip + "\n可使用\"-ip 123.123.123.123\"类似参数定义db地址!");
        }
        try {
            run();
        } catch (Exception e) {
            System.out.println(ConsoleColors.GREEN + "ERROR!-异常-\n请联系管理员或重新尝试~" + ConsoleColors.RESET);
            exit();
        }
    }
}

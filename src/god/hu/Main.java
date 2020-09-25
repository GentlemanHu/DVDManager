package god.hu;

import god.hu.usage.MyLab;
import god.hu.usage.tool.cli.ConsoleColors;

public class Main extends MyLab {
    public static void main(String[] args) throws Exception {
        try {
            run();
        } catch (Exception e) {
            System.out.println(ConsoleColors.GREEN+"ERROR!-异常-\n请联系管理员或重新尝试~"+ConsoleColors.RESET);
            exit();
        }
    }
}

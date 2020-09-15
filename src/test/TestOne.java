package test;

import god.hu.cli.TableRender;

public class TestOne {
    private static TableRender render = new TableRender();

    public static void main(String[] args) {
        // testOne();
       // printStar(10);
    }

    public static void testOne() {
        render.setShowVerticalLines(true);
        render.setHeaders("one", "two", "three");
        render.addRow("super", "broccoli", "flexible");
        render.addRow("nice", "pleasant", "wild");
        render.print();

        render = new TableRender.Builder()
                .setShowVerticalLines(true)
                .setHeaders("one", "two", "three")
                .addRow("super", "broccoli", "flexible")
                .addRow("nice", "pleasant", "wild")
                .addRow("jjj", "jjkas", "lasjdfkla")
                .build();

        System.out.println("new Render from builder");

        render.print();
    }

    // hundred chickens
    public static void getChick() {
        for (int x = 0; x < 20; x++)
            for (int y = 0; y < 33; y++)
                for (int z = 0; z < 100; z++)
                    if (5 * x + 3 * y + (1 / 3) * z == 100 && x + y + z == 100)
                        System.out.println("cock:" + x + "hen:" + y + "chick:" + z);
    }

    // print starts
    public static void printStar(int lines) {
        for (int i = 0; i < lines; i++) {
            int tmp = i;
            while (tmp > 0) {
                System.out.print("*");
                tmp--;
            }
            System.out.print("\n");
        }
    }
}

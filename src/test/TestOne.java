
import god.hu.usage.tool.SerialNumberGenerator;
import god.hu.usage.tool.cli.TableRender;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TestOne {
    private static TableRender render = new TableRender();


    @Test
    public void testDate(){
        Date date = new Date();

        System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
    }
    @Test
    public void testSerial(){
        for (int i = 0; i < 10; i++) {

            System.out.println(new SerialNumberGenerator(7).generate());
        }
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

    public static void findFlower2(){
        int num = 100; int a[] = new int[3];
        System.out.print("3位的水仙花数有：\t");
        while (num <= 999) {
            int sum =0;
            a[0] = num / 100 % 10;
            a[1] = num / 10 % 10;
            a[2] = num % 10;
            for (int i = 0; i < 3; i++) {
                sum = sum + (int) Math.pow(a[i], 3);
            }
            if (num ==sum) {
                System.out.print(num + "\t"); } num++;
        }
    }


   // 1^3+5^3+3^3=153
    public static void findFlower(){
            for (int o=0;o<1000;o++)
                for (int t=0;t<1000;t++)
                    for(int r=0;r<1000;r++)
                        if(Math.pow(o,3)+Math.pow(t,3)+Math.pow(r,3)==(o*100+t*10+r))
                            System.out.println("first:"+o+"second:"+t+"third:"+r);
    }


    // hundred chickens
    public static void getChick() {
        for (int x = 0; x < 20; x++)
            for (int y = 0; y < 33; y++)
                for (int z = 0; z < 100; z++)
                    if (5 * x + 3 * y + (1 / 3) * z == 100 && x + y + z == 100)
                        System.out.println("cock:" + x + "hen:" + y + "chick:" + z);
    }



    // always print star
    public static void alwaysPrintStar(){
        for(;;){
            printStar(ThreadLocalRandom.current().nextInt(3,20));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

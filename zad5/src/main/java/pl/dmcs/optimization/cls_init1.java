package pl.dmcs.optimization;

import java.util.Scanner;

/**
 * Created by Dawid on 08.12.2018 at 15:10.
 */
// class initialization
public class cls_init1 {
    static class Data {
        private int month;
        private String name;
        Data(int i, String s) {
            month = i;
            name = s;
        }
    }
    Data months[] = {
            new Data(1, "January"),
            new Data(2, "February"),
            new Data(3, "March"),
            new Data(4, "April"),
            new Data(5, "May"),
            new Data(6, "June")
    };
    public static void main(String args[]) {
        new Scanner(System.in).nextLine();
        final int N = 1000000000;
        cls_init1 x;
        Timer t = new Timer();
        for (int i = 1; i <= N; i++)
            x = new cls_init1();
        t.print("I am inefficient");

        System.out.println("exit");
        new Scanner(System.in).nextLine();
    }
}


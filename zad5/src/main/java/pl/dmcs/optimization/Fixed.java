package pl.dmcs.optimization;

import java.util.Scanner;

/**
 * Created by Dawid on 08.12.2018 at 15:30.
 */
public class Fixed {
    Data months[] = {
            Data.JAUNARY,
            Data.FEBRUARY,
            Data.MARCH,
            Data.APRIL,
            Data.MAY,
            Data.JUNE
    };
    public static void main(String args[]) {
        new Scanner(System.in).nextLine();
        final int N = 1000000000;
        Fixed x;
        Timer t = new Timer();
        for (int i = 1; i <= N; i++)
            x = new Fixed();
        t.print("I am efficient");

        System.out.println("exit");
        new Scanner(System.in).nextLine();
    }
}

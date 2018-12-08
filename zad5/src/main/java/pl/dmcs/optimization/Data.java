package pl.dmcs.optimization;

/**
 * Created by Dawid on 08.12.2018 at 15:31.
 */
public enum  Data {
    JAUNARY(1, "January"),
    FEBRUARY(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April"),
    MAY(5, "May"),
    JUNE(6, "June");

    private int month;
    private String name;
    Data(int i, String s) {
        month = i;
        name = s;
    }
}

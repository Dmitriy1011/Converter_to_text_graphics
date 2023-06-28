package ru.netology.graphics.image;

public class ColorScheme implements TextColorSchema {
    private static final char GRID = '#';
    private static final char DOLLAR = '$';
    private static final char AT = '@';
    private static final char PERCENT = '%';
    private static final char ASTERISK = '*';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char QUOTE = '`';

    private static final char MIN = 0;
    private static final char MAX = 255;


    @Override
    public char convert(int color) {

        double intervalNum = (MAX / 8);

        if(color < intervalNum) {
            return GRID;
        } else if(color >= intervalNum && color < (intervalNum * 2)) {
            return DOLLAR;
        } else if(color >= (intervalNum * 2) && color < (intervalNum * 3)) {
            return AT;
        } else if(color >= (intervalNum * 3) && color < (intervalNum * 4)) {
            return PERCENT;
        } else if(color >= (intervalNum * 4) && color < (intervalNum * 5)) {
            return ASTERISK;
        } else if(color >= (intervalNum * 5) && color < (intervalNum * 6)) {
            return PLUS;
        } else if(color >= (intervalNum * 6) && color < (intervalNum * 7)) {
            return MINUS;
        } else if(color >= (intervalNum * 7) && color < (intervalNum * 8)) {
            return QUOTE;
        }
        return '?';
    }
}

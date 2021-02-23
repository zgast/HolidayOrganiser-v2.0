package at.markus.holidayorganiser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Computing {
    public int days(int day1, int month1, int year1, int day2, int month2, int year2){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = String.format("%02d", day1) + " " + String.format("%02d", month1) + " " + year1;
        String inputString2 = String.format("%02d", day2) + " " + String.format("%02d", month2) + " " + year2;

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 =  myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            return (int)(diff / (1000*60*60*24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static int weekDay(int tag, int month, int year){
        int weekDay;

        int weekDayTemporary=(year-1900)*365 + (year-1900)/4;

        if (year %4==0 && month<=2){
            weekDayTemporary--;
        }


        switch (month){
            case 12: weekDayTemporary +=30;
            case 11: weekDayTemporary +=31;
            case 10: weekDayTemporary +=30;
            case 9: weekDayTemporary +=31;
            case 8: weekDayTemporary +=31;
            case 7: weekDayTemporary +=30;
            case 6: weekDayTemporary +=31;
            case 5: weekDayTemporary +=30;
            case 4: weekDayTemporary +=31;
            case 3: weekDayTemporary +=28;
            case 2: weekDayTemporary +=31;
        }

        weekDay=(tag+weekDayTemporary)%7;

        return weekDay;
    }
}
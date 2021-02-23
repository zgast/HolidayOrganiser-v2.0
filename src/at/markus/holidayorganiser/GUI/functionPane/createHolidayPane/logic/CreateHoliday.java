package at.markus.holidayorganiser.GUI.functionPane.createHolidayPane.logic;

import at.markus.holidayorganiser.Computing;
import at.markus.holidayorganiser.excel.RestDaysComputing;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=false)
public class CreateHoliday {
    final Computing computing = new Computing();
    final RestDaysComputing RestDaysComputing = new RestDaysComputing();
    Methods methods;
    int month1 ,month2,year1,year2,day1,day2;


    public void createHoliday(String name, boolean[] weekDays, LocalDate date1, LocalDate date2){
        Dates(date1,date2);
        methods = new Methods(name,month1,month2,day1,day2,year1,year2);

        //Sonntag == 0 ... Samstag == 6
        int weekDay=Computing.weekDay(day1,month1,year1);
        int weekDay2=Computing.weekDay(day2,month2,year2);

        int weekFree = methods.weekFree(weekDays);
        int first = methods.first(weekDays,weekDay);
        int last = methods.last(weekDays,weekDay2);
        int firstDays = computing.days(day1,month1,year1,day2,month2,year2);
        // 0 = year1, 1 = year1-1, 2 = year1-1 & year 1, 3 = year1 & year2
        byte[] proof = RestDaysComputing.proof(day1,month1,year1,day2,month2,year2,name);

        methods.appointmentCreate(firstDays);
        methods.createHoliday(weekFree,first,last,firstDays,proof,weekDay,weekDay2);

    }
    private void Dates(LocalDate date, LocalDate date2){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String[] tmpDate = dateFormat.format(java.sql.Date.valueOf(date)).split("/");
        day1 = Integer.parseInt(tmpDate[2]);
        month1 = Integer.parseInt(tmpDate[1]);
        year1 =Integer.parseInt(tmpDate[0]);

        tmpDate = dateFormat.format(java.sql.Date.valueOf(date2)).split("/");
        day2 = Integer.parseInt(tmpDate[2]);
        month2 = Integer.parseInt(tmpDate[1]);
        year2 =Integer.parseInt(tmpDate[0]);
    }

}

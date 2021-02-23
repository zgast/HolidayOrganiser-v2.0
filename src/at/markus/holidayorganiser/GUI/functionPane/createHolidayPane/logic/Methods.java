package at.markus.holidayorganiser.GUI.functionPane.createHolidayPane.logic;

import at.markus.holidayorganiser.Computing;
import at.markus.holidayorganiser.calendar.CalendarAppointment;
import at.markus.holidayorganiser.excel.RestDaysAdding;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=false)
public class Methods {
    final CalendarAppointment CalendarAppointment = new CalendarAppointment();
    final RestDaysAdding RestDaysAdding = new RestDaysAdding();
    final Computing computing = new Computing();
    final int month1;
    final int month2;
    final int year1;
    final int year2;
    final int day1;
    final int day2;
    final String name;

    public int weekFree(boolean[] weekDays) {
        int count=0;
        for(int i=0;i<weekDays.length;i++){
            if(weekDays[i]){ count++;}
        }
        return count;
    }
    public void appointmentCreate(int firstDays){
        CalendarAppointment.appointmentCreate(name, day1, month1, year1, firstDays);
    }

    public int first(boolean[] weekDays, int weekDay) {
        int count=0;
        for(int i=weekDay;i<weekDays.length;i++){
            if(weekDays[i]){ count++;}
        }
        return count;
    }

    public void createHoliday(int weekFree, int first, int last, int firstDays, byte[] proof, int weekDay, int weekDay2){
        if(proof[0]==0) {
            firstDays =firstDays-(7-weekDay) -weekDay2;
            int days = (firstDays/7*weekFree)+last+first;
            RestDaysAdding.createRestDays(days, year1, name);
        }else if(proof[0]==1){
            firstDays =firstDays-(7-weekDay) -weekDay2;
            int days = (firstDays/7*weekFree)+last+first;
            RestDaysAdding.createRestDays(days, year1-1, name);
        }else if(proof[0]==2){
            int tmpDays1 = computing.days(day1,month1,year1,proof[1],proof[2],year1);
            int tmpDays2 = computing.days(proof[1],proof[2],year1,day2,month2,year2);

            RestDaysAdding.createRestDays((tmpDays1-(7-weekDay))/7*weekFree+first, year1-1, name);
            RestDaysAdding.createRestDays((tmpDays2-weekDay2)/7*weekFree+last, year1, name);
        }else if(proof[0]==3){
            int tmpDays1 = computing.days(day1,month1,year1,proof[1],proof[2],year1);
            int tmpDays2 = computing.days(proof[1],proof[2],year1,day2,month2,year2);

            RestDaysAdding.createRestDays((tmpDays1-(7-weekDay))/7*weekFree+first, year1, name);
            RestDaysAdding.createRestDays((tmpDays2-weekDay2)/7*weekFree+last, year2, name);
        }
    }
    public int last(boolean[] weekDays, int weekDay) {
        int count=0;
        for(int i=0;i<=weekDay;i++){
            if(weekDays[i]){ count++;}
        }
        return count;
    }
    public Methods(String name, int month1, int month2, int day1, int day2, int year1, int year2){
        this.name = name;
        this.month1 = month1;
        this.month2 = month2;
        this.year1 = year1;
        this.year2 = year2;
        this.day1 = day1;
        this.day2 = day2;
    }
}

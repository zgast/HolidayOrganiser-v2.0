package at.markus.holidayorganiser.excel;

import at.markus.holidayorganiser.Worker;

public class RestDaysComputing {

    public byte[] proof(int day1, int month1, int year1, int day2, int month2, int year2, String name) {
        byte[] result = new byte[3];

        Worker worker = new Worker(name);
        int joinMonth = worker.getYear();
        int joinDay = worker.getMonth();

        if (year1 == year2) {
            if (month2 == joinMonth && day2 < joinDay) {
                result[0] = 1;
                return result;
            }
            else if (month1 < joinMonth && month2 < joinMonth) {
                result[0] = 1;
                return result;
            }
            else if (month1 <= joinMonth && month2 >= joinMonth) {
                if(month1 < joinMonth||month1 == joinMonth && day1 < joinDay){
                    if(month2>joinMonth||joinMonth ==month2 &&day2>=joinDay){result[0] = 2;return result;}
                }
                else if(month2> joinMonth ||month2 == joinMonth && day2 >= joinDay){
                    if(month1<joinMonth||joinMonth ==month1 &&day1<joinDay){result[0] = 2;return result;}
                }
                else if(month1 < joinMonth && month2 > joinMonth){ result[0] = 2;return result;}
            }

        } else {
            if (month2<joinMonth||month2 == joinMonth && day2 <= joinDay) {
                if (month1 == joinMonth && day1 < joinDay || month1 < joinMonth) { result[0] = 2;return result;
                } else {
                    result[0] = 0;return result;
                }
            }else{ result[0] = 3;return result;}
        }

        result[0] = 0;
        return result;
    }
}
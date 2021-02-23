package at.markus.holidayorganiser.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class CalendarAppointment {
    private int rngID;
    private final Random rng = new Random();
    public  void appointmentCreate(String name, int day, int month, int year, int days){
        rngID = rng.nextInt(999999);
        String FilenameAndPath = "Data\\AppointmentFiles\\" + name + ".csv";

        String before = readCSV(name);
        File file = new File(FilenameAndPath);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(before);

        for(int i=days;0<i;){
            int length;
            if(month==2){ length=28; }
            else if(month%2==0){ length=30; }
            else{ length=31; }

            if(length>i){
                length=i;
            }

            for(int j=day;j<=length;j++){
                builder.append("Urlaub"+","+year+","+month+","+j+","+rngID+"\n");
            }
            i-=length;
            day=1;
            if(month==12){
                month=0;
                year++;
            }else{
                month++;
            }
        }

        pw.write(builder.toString());
        pw.flush();
        pw.close();
    }

    public static String readCSV(String name) {
        String FilenameAndPath = "Data\\AppointmentFiles\\" + name + ".csv";
        String column="";
        String before="";
        try {
            java.io.BufferedReader FileReader=
                    new java.io.BufferedReader(
                            new java.io.FileReader(
                                    new java.io.File(FilenameAndPath)
                            )
                    );

            while(null!=(column=FileReader.readLine())){
                before+=column+"\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return before;
    }

}



package at.markus.holidayorganiser;

import at.markus.holidayorganiser.excel.RestDaysAdding;
import at.markus.holidayorganiser.excel.WorkerHandle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Worker {
    public static String name;
    final RestDaysAdding restDaysAdding = new RestDaysAdding();
    final WorkerHandle workerHandle = new WorkerHandle();
    int year=0;
    int month=0;
    int day=0;

    public Worker(String name){
        Worker.name = name;
        try{
            setup();
        }catch(Exception e){ }
    }

    public int getRestDays(int year){
        return restDaysAdding.getRestdays(name,year);
    }
    public  void delete(){
        try {
            workerHandle.deleteWorker(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void create(int day, int month, int year, int days){
        workerHandle.createWorker(name,days, day,month,year);
        setup();
    }
    private void setup(){
        int[] tmp = workerHandle.getStart(name);
        day = tmp[0];
        month = tmp[1];
        year = tmp[2];
    }
}

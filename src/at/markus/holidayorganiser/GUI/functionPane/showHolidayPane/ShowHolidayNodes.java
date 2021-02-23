package at.markus.holidayorganiser.GUI.functionPane.showHolidayPane;

import at.markus.holidayorganiser.Worker;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ShowHolidayNodes {
    private final Worker worker;
    private int restdays = 0;

    public ShowHolidayNodes(Worker worker){
        this.worker = worker;
    }

    public Label yearDisplay(int year, boolean bool){
        if(bool){
            int tmp = worker.getRestDays(year);
            restdays += tmp;

            return label("Der/die Mitarbeiter/in "+ Worker.name +" hat noch "+
                     tmp +" Tage Urlaub, in dem Jahr "+ year + "/" + (year +1) +".");
        }else{
            return label("");
        }
    }

    public VBox lastHolidays(){
        VBox box = new VBox();
        box.setTranslateX(200);
        String[][] holidays = getHolidays();
        box.getChildren().add(label("Letzten Urlaube:"));
        int count = 0;
        for(int i=0;i<21;i++){
            if(holidays[i][0]!=null){
                count++;
                box.getChildren().add(label(holidays[i][0] + " - " + holidays[i][1]));
            }else{
                box.getChildren().add(label(""));
            }
        }
        box.getChildren().add(label(count +"/20"));
        return box;
    }

    public Label label(String text){
        Label label = new Label(text);
        label.setPrefSize(500,40);
        label.setAlignment(Pos.CENTER);

        return label;
    }

    private String[][] getHolidays(){
        String FilenameAndPath = "Data\\AppointmentFiles\\" + Worker.name + ".csv";
        String text="";
        String[][] holidays = new String[21][2];

        try {
            BufferedReader FileReader = new BufferedReader(new FileReader(new File(FilenameAndPath)));
            String column = "";
            while(null!=(column=FileReader.readLine())){
                text+=column +","+"\n";
            }

            String[] lines= text.split(",");
            String start = null;
            String before = null;
            int count=0;

            for(int i=4;i<lines.length;i+=5){
                if(before==null){
                    before=lines[i];
                    start = lines[i-1]+"."+lines[i-2]+"."+lines[i-3];
                }else if(!lines[i].equals(before)){
                    before  = lines[i];
                    holidays[count][0] = start;
                    holidays[count][1] = lines[i-6]+"."+lines[i-7]+"."+lines[i-8];
                    start =  lines[i-1]+"."+lines[i-2]+"."+lines[i-3];
                    count ++;
                }else if(i== lines.length-2){
                    holidays[count][0] = start;
                    holidays[count][1] = lines[i-1]+"."+lines[i-2]+"."+lines[i-3];
                }
                else if(count>20){ break;}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return holidays;
    }

    public int getRestdays() {
        return this.restdays;
    }
}

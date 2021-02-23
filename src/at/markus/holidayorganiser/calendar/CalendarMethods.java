package at.markus.holidayorganiser.calendar;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=false)
public class CalendarMethods {

     BorderPane main = new BorderPane();
    final BorderPane containerPane = new BorderPane();
    final LocalDateTime date = LocalDateTime.now();
    String appointmentFile = null;
    HBox txtGroup;
    int curr_year;
    int curr_month;
    final List<Label> active_click = new ArrayList();
    Button left, year, today, right;

    public BorderPane start(){
        setupTopPane();
        GridPane gp = setupMonthPane(date.getYear(), date.getMonthValue());
        setupTopPane();
        colorButtons();
        actionEvent(curr_year,curr_month);
        containerPane.setCenter(gp);
        main.setCenter(containerPane);
        containerPane.setCenter(twelveMonthsPane());
        containerPane.setCenter(setupMonthPane(date.getYear(),date.getMonthValue()));
        Text m = (Text)txtGroup.getChildren().get(0);
        Text y = (Text)txtGroup.getChildren().get(1);
        y.setText(date.getYear() + "");
        m.setText(new DateFormatSymbols().getMonths()[date.getMonthValue()-1].toUpperCase());
        return containerPane;
    }

    void colorButtons(){
        left.setStyle(  "-fx-background-color: #36393f;" +
                "-fx-border-color: white; " +
                "-fx-text-fill: white;");
        year.setStyle(  "-fx-background-color: #36393f;" +
                "-fx-border-color: white; " +
                "-fx-text-fill: white;");
        today.setStyle(  "-fx-background-color: #36393f;" +
                "-fx-border-color: white; " +
                "-fx-text-fill: white;");
        right.setStyle(  "-fx-background-color: #36393f;" +
                "-fx-border-color: white; " +
                "-fx-text-fill: white;");
    }
    public void setupTopPane()
    {
        BorderPane nav = new BorderPane();
        containerPane.setTop(nav);

        txtGroup = new HBox(20);
        Text tm = new Text(date.getMonth() + "");
        Text ty = new Text(date.getYear() + "");
        tm.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        ty.setFont(Font.font("Arial",FontWeight.LIGHT, FontPosture.REGULAR, 20));
        ty.setFill(Color.WHITE);
        tm.setFill(Color.WHITE);
        txtGroup.getChildren().addAll(tm,ty);
        nav.setLeft(txtGroup);

        HBox btnGroup = new HBox(10);
        left = new Button("<");
        year = new Button("    Year    ");
        today = new Button("    Today    ");
        right = new Button(">");
        btnGroup.getChildren().addAll(left,year,today,right);
        nav.setRight(btnGroup);

        nav.setPadding(new Insets(10,10,0,10));
    }

    GridPane setupMonthPane(int yearValue, int monthValue)
    {

        curr_year = yearValue;
        actionEvent(yearValue,monthValue);
        GridPane monthPane = new GridPane();


        for(int i=0;i<7;i++)
        {
            Label m = new Label("S");
            switch(i)
            {
                case 0:
                case 6:
                    m.setText("S");
                    break;
                case 1:
                    m.setText("M");
                    break;
                case 2:
                case 4:
                    m.setText("T");
                    break;
                case 3:
                    m.setText("W");
                    break;
                default:
                    m.setText("F");
                    break;
            }

            m.prefWidthProperty().bind(containerPane.widthProperty());
            m.setAlignment(Pos.CENTER);
            m.setFont(new Font(15));
            m.setStyle("-fx-border-color: white;"+
                    "-fx-text-fill: white");

            monthPane.add(m, i, 0);
        }

        for(int i=1;i<7;i++)
        {
            for(int j=0;j<7;j++)
            {
                Label d = new Label("");
                d.prefWidthProperty().bind(containerPane.widthProperty());
                d.prefHeightProperty().bind(containerPane.heightProperty());
                d.setStyle("-fx-border-color: white;"+
                        "-fx-text-fill: white");

                monthPane.add(d, j, i);
            }
        }

        monthPane.setPadding(new Insets(10,10,20,10));

        fillUpMonth(monthPane, yearValue, monthValue);

        return monthPane;
    }

    void fillUpMonth(GridPane monthGP, int yearValue, int monthValue)
    {
        int beginDay = 0;
        int daysInMonth = 0;
        int col = 0;
        int row = 0;
        int curr_day = date.getDayOfMonth();
        LocalDateTime curr_date = date.withYear(yearValue);
        curr_date = curr_date.withMonth(monthValue);
        if(monthValue == 1)
        {
            YearMonth lastYear = YearMonth.of(yearValue-1, 12);
            beginDay = lastYear.lengthOfMonth();

            YearMonth thisYear = YearMonth.of(yearValue, monthValue);
            daysInMonth = thisYear.lengthOfMonth();
        }
        else
        {
            YearMonth lastYear = YearMonth.of(yearValue, monthValue-1);
            beginDay = lastYear.lengthOfMonth();

            YearMonth thisYear = YearMonth.of(yearValue, monthValue);
            daysInMonth = thisYear.lengthOfMonth();
        }

        LocalDateTime d = curr_date.withDayOfMonth(1);
        LocalDateTime ld = curr_date.withDayOfMonth(daysInMonth);

        ObservableList<Node> date_label = monthGP.getChildren();

        int i = 1, j=1, k = beginDay - (d.getDayOfWeek().getValue()-1);
        for(Node node : date_label)
        {
            Label s = (Label)node;
            s.setAlignment(Pos.CENTER);
            s.setTextFill(Color.WHITE);
            s.setFont(new Font(15));
            if(GridPane.getRowIndex(node) > 0)
            {
                if(d.getDayOfWeek().getValue() > 6)
                {
                    if(i==curr_day && (date.getMonth().getValue() == monthValue && date.getYear() == yearValue))
                    {
                        active_click.add(s);
                        col = GridPane.getColumnIndex(node);
                        row = GridPane.getRowIndex(node);
                        i++;
                    }
                    else
                    {
                        if(GridPane.getRowIndex(node) > 0 && i<=daysInMonth)
                        {
                            active_click.add(s);
                            s.setText(i + "");
                            i++;
                        }
                        else if(i>daysInMonth)
                        {
                            s.setText(j+"");
                            s.setTextFill(Color.GREY);
                            j++;
                        }
                        else {
                            s.setText(k+"");
                            s.setTextFill(Color.GREY);
                            k++;
                        }
                    }
                }
                else
                {
                    if(i==curr_day && (date.getMonth().getValue() == monthValue && date.getYear() == yearValue))
                    {
                        active_click.add(s);
                        col = GridPane.getColumnIndex(node);
                        row = GridPane.getRowIndex(node);
                        i++;
                    }
                    else
                    {
                        if((GridPane.getColumnIndex(node) >= d.getDayOfWeek().getValue() || GridPane.getRowIndex(node) > 1) && i<=daysInMonth)
                        {
                            active_click.add(s);
                            s.setText(i + "");
                            i++;
                        }
                        else if(i>daysInMonth)
                        {
                            s.setText(j+"");
                            s.setTextFill(Color.GREY);
                            j++;
                        }
                        else {
                            s.setText(k+"");
                            s.setTextFill(Color.GREY);
                            k++;
                        }
                    }

                }

            }

        }
        if(date.getMonth().getValue() == monthValue && date.getYear() == yearValue)
        {
            Label curr_label = new Label(date.getDayOfMonth()+ "", new Circle(15,Color.web("FF0000")));
            curr_label.setContentDisplay(ContentDisplay.CENTER);
            curr_label.setTextFill(Color.WHITE);
            curr_label.setAlignment(Pos.CENTER);
            curr_label.prefWidthProperty().bind(containerPane.widthProperty());
            curr_label.prefHeightProperty().bind(containerPane.heightProperty());
            curr_label.setFont(new Font(20));
            monthGP.add(curr_label, col, row);
        }
        try {
            displayAppointments(monthGP, yearValue,monthValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    GridPane twelveMonthsPane()
    {
        GridPane twelve = new GridPane();


        Text m = (Text)txtGroup.getChildren().get(0);

        m.setText("");
        int month = 1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                VBox temp = new VBox();
                Label t = new Label(new DateFormatSymbols().getMonths()[month-1]);
                t.prefWidthProperty().bind(temp.widthProperty());
                t.setAlignment(Pos.CENTER);
                GridPane remove = setupMonthPane(curr_year,month);

                for(Node node : remove.getChildren())
                {
                    Label remove_label = (Label)node;
                    remove_label.setStyle("-fx-border-color: whitesmoke;");
                }

                temp.getChildren().addAll(t,remove);
                twelve.add(temp, j, i);
                month++;
            }
        }


        actionEvent(curr_year,curr_month);


        return twelve;
    }

    void actionEvent(int yearValue,int monthValue)
    {
        left.setOnAction(e -> {
            if(monthValue == 1)
            {
                containerPane.setCenter(setupMonthPane(yearValue-1,12));
                Text m = (Text)txtGroup.getChildren().get(0);
                Text y = (Text)txtGroup.getChildren().get(1);
                y.setText((yearValue - 1) + "");
                m.setText(new DateFormatSymbols().getMonths()[11].toUpperCase());
            }
            else
            {
                containerPane.setCenter(setupMonthPane(yearValue,monthValue-1));
                Text m = (Text)txtGroup.getChildren().get(0);
                m.setText(new DateFormatSymbols().getMonths()[monthValue-2].toUpperCase());
            }
        });
        right.setOnAction(e -> {
            if(monthValue == 12)
            {
                containerPane.setCenter(setupMonthPane(yearValue+1,1));
                Text m = (Text)txtGroup.getChildren().get(0);
                Text y = (Text)txtGroup.getChildren().get(1);
                y.setText((yearValue + 1) + "");
                m.setText(new DateFormatSymbols().getMonths()[0].toUpperCase());
            }
            else
            {
                containerPane.setCenter(setupMonthPane(yearValue,monthValue+1));
                Text m = (Text)txtGroup.getChildren().get(0);
                m.setText(new DateFormatSymbols().getMonths()[(monthValue)].toUpperCase());
            }
        });
        year.setOnAction(e -> {
            curr_year = yearValue;
            curr_month = monthValue;
            containerPane.setCenter(twelveMonthsPane());
        });
        today.setOnAction(e -> {
            containerPane.setCenter(setupMonthPane(date.getYear(),date.getMonthValue()));
            Text m = (Text)txtGroup.getChildren().get(0);
            Text y = (Text)txtGroup.getChildren().get(1);
            y.setText(date.getYear() + "");
            m.setText(new DateFormatSymbols().getMonths()[date.getMonthValue()-1].toUpperCase());
        });
    }

    void displayAppointments(GridPane monthPane,int yearValue,int monthValue) throws Exception
    {
        List<List<String>> records = new ArrayList<>();
        List<Integer> col = new ArrayList();
        List<Integer> record_i = new ArrayList();
        List<Integer> row = new ArrayList();
        try(BufferedReader br = new BufferedReader(new FileReader(appointmentFile)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }

        for(int i=0;i<records.size();i++)
        {
            for(int j=0;j<records.get(i).size(); j++)
            {
                if(j == 1 || j == 2)
                {
                    if(Integer.parseInt(records.get(i).get(j)) == yearValue && Integer.parseInt(records.get(i).get(j+1)) == monthValue)
                    {

                        for(Node n : monthPane.getChildren())
                        {
                            Label t = (Label)n;


                            if(!t.getText().isEmpty()  && (t.getTextFill() != Color.GREY) && (GridPane.getRowIndex(n) > 0) && (Integer.parseInt(records.get(i).get(3)) == Integer.parseInt(t.getText())))
                            {
                                col.add(GridPane.getColumnIndex(n));
                                row.add(GridPane.getRowIndex(n));
                                record_i.add(i);
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<record_i.size();i++)
        {
            Label t = new Label("    "+records.get(record_i.get(i)).get(0)+"\n"+"("+records.get(record_i.get(i)).get(3)+"."+records.get(record_i.get(i)).get(2)+"."+records.get(record_i.get(i)).get(1)+")");
            t.setTextFill(Color.GREEN);
            t.setPadding(new Insets(0,0,20,0));
            t.setFont(new Font(15));
            t.prefWidthProperty().bind(containerPane.widthProperty());
            t.prefHeightProperty().bind(containerPane.heightProperty());
            t.setAlignment(Pos.BOTTOM_CENTER);
            monthPane.add(t, col.get(i), row.get(i));
            t.setStyle("-fx-background-color: grey;" + "-fx-border-color: white;"+"-fx-text-fill: blue");
        }
    }


    public void setMain(BorderPane main) {
        this.main = main;
    }
    public void setAppointmentFile(String appointmentFile) { this.appointmentFile = appointmentFile; }
}

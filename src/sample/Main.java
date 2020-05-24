package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {
    
    public void start(Stage stage) throws Exception{
        final int n=10;
        final int N=256;
        final int W=2700;

        double A = Math.random();
        double f = Math.random();
        stage.show();

        stage.setTitle("Сигналища");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("N");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Графік випадкових сигналів");
        XYChart.Series series = new XYChart.Series();


        double [] x=new double[N];
        System.out.println("A="+A);
        System.out.println("f="+f);
        float Wp=W/n;
        for (int t=1; t<N; t++) {
            for (int i = 1; i < n; i++) {
                x[t] = (x[t] + A * Math.sin(Wp * t + f));
                Wp+=W/n;
            }
        }
        double Mx = 0,Dx=0;
        for (int i=1; i<N; i++){
            series.getData().add(new XYChart.Data(i, x[i]));
            System.out.println(i+" ");
            System.out.println(x[i]+" ");
            Mx=Mx+x[i];
        }
        Mx=Mx/N;
        System.out.println("Mx="+Mx);
        for (int i=1; i<N; i++) {
            Dx=Dx+Math.pow((x[i]-Mx),2);
        }
        Dx=Dx/(N-1);
        System.out.println("Dyspersion="+Dx);
        String result=String.format("Mx=%3.3f and Dx=%3.3f", Mx, Dx);
        series.setName(result);
        Scene scene  = new Scene(lineChart,1200,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args);
    }
}

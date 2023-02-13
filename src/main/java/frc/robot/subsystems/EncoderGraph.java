import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class EncoderGraph extends Application {
  static List<EncoderData> data = new ArrayList<EncoderData>();
  
  public static void graphEncoderPosition() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Encoder Position");
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Time (in seconds)");
    yAxis.setLabel("Encoder Position");
    final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
    lineChart.setTitle("Encoder Position Over Time");
    XYChart.Series series = new XYChart.Series();
    series.setName("Encoder Position");
    for (EncoderData d : data) {
      series.getData().add(new XYChart.Data(d.time, d.position));
    }
    Scene scene  = new Scene(lineChart,800,600);
    lineChart.getData().add(series);
    stage.setScene(scene);
    stage.show();
  }
}

class EncoderData {
  long time;
  double position;
  
  public EncoderData(long time, double position) {
    this.time = time;
    this.position = position;
  }
}

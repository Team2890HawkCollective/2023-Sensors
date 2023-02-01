package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;

public class LineReader extends SubsystemBase{

// private static DigitalInput Sensor2 = new DigitalInput(Constants.CHANNEL_2);
// private static DigitalInput Sensor3 = new DigitalInput(Constants.CHANNEL_3);
// private static DigitalInput Sensor4 = new DigitalInput(Constants.CHANNEL_4);
private final static I2C.Port i2cPort = I2C.Port.kOnboard;
private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

public static boolean isLine()
{
    Color detectedColor = m_colorSensor.getColor();

    int colorValueDecimal = detectedColor.hashCode();

    //System.out.println(colorValueDecimal/1000000);

    //double IR = m_colorSensor.getIR();

    //System.out.println("DEBUG 4");

    //System.out.println(detectedColor);

    // String detectedColorString = detectedColor.toString();

    // int colorValue =Character.getNumericValue(detectedColorString.charAt(1));

    //System.out.println(colorValue);

    if (colorValueDecimal > 800000000)
    {
      System.out.println("BLUE");
    }

    else if (colorValueDecimal < -1700000000)
    {
      System.out.println("RUG");
    }

    else if (colorValueDecimal < -600000000 && colorValueDecimal > -1700000000)
    {
      System.out.println("RED");
    }
    else
    {
      System.out.println("OUTSIDE PRESET BOUNDS: " + colorValueDecimal);
    }

    return true;
}

public static void passedLine()
{
  // int leftFrontColorSensor = 0;
  // int rightFrontColorSensor = 0;
  // int leftRearColorSensor = 0;
  // int rightReartColorSensor = 0;


}

public LineReader() {}

@Override
public void periodic() {
  // This method will be called once per scheduler run

  
}

@Override
public void simulationPeriodic() {
  // This method will be called once per scheduler run during simulation
}
}

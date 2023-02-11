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

private static final int MULTIPLEXER_ADDRESS = 0x70;
private static final int COLOR_SENSOR_1_ADRESS = 0x29;

private final static I2C i2cBus = new I2C(i2cPort, MULTIPLEXER_ADDRESS);

private final static I2C colorSensor1 = new I2C(i2cPort, COLOR_SENSOR_1_ADRESS);
// private final static I2C colorSensor2 = new I2C(i2cPort, 0x2A);
// private final static I2C colorSensor3 = new I2C(i2cPort, 0x2B);
// private final static I2C colorSensor4 = new I2C(i2cPort, 0x2C);


//private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
//private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard, 0x29);

public static boolean isLine(int sensorNumber)
{
  //Get the color detected by the right sensor using sensorNumber

  // System.out.println(colorSensor3.addressOnly() + " " + colorSensor3.getDeviceAddress());
  // System.out.println(colorSensor4.addressOnly() + " " + colorSensor4.getDeviceAddress());

  

  //Get the color detected by the a single sensor using sensor without the multiplexer TCA9548A

  /* 
    Color detectedColor = m_colorSensor.getColor();

    int colorValueDecimal = detectedColor.hashCode();

    if (colorValueDecimal > 800000000)
    {
      System.out.println("BLUE");
      return true;
    }

    else if (colorValueDecimal < -1700000000)
    {
      System.out.println("RUG");
      return false;
    }

    else if (colorValueDecimal < -600000000 && colorValueDecimal > -1700000000)
    {
      System.out.println("RED");
      return true;
    }
    else
    {
      System.out.println("OUTSIDE PRESET BOUNDS: " + colorValueDecimal);
    }
*/
    return false;

}

public static int[] readColor()
{
  // colorSensor1.write(COLOR_SENSOR_1_ADRESS, 0X16);

  byte[] buffer = new byte[8];
  // colorSensor1.read(COLOR_SENSOR_1_ADRESS, 8, buffer);

  // int[] color = new int[4];
  // for (int i = 0; i < 4; i++)
  // {
  //   color[i] = buffer[i * 2] & 0xff;
  //   color[i] |= (buffer[i * 2 + 1] & 0xff) << 8;
  // }

  // System.out.println(buffer);
  // System.out.println(color);
  // System.out.println("Red: " + color[0]);
  // System.out.print(" Green: " + color[1]);
  // System.out.print(" Blue: " + color[2]);
  // System.out.print(" Clear: " + color[3]);

  int[] color = new int[8];
  colorSensor1.read(COLOR_SENSOR_1_ADRESS, 8, buffer);
  return color;
}

public static void interpretColor(int[] color)
{
  // System.out.println("Red: " + color[0]);
  // System.out.print(" Green: " + color[1]);
  // System.out.print(" Blue: " + color[2]);
  // System.out.print(" Clear: " + color[3]);


}

public static boolean passedLine()
{
  boolean sensor1 = false;
  boolean sensor2 = false;
  boolean sensor3 = false;
  boolean sensor4 = false;

  if (isLine(1))
  {
    sensor1 = true;
  }

  if (isLine(2))
  {
    sensor2 = true;
  }
  
  if (isLine(3))
  {
    sensor3 = true;
  }

  if (isLine(4))
  {
    sensor4 = true;
  }

  if (sensor1 && sensor2 && sensor3 && sensor4)
  {
    //SET MOTORS TO DRIVE FORWARD ANOTHER METER
    return true;
  }

  else return false;
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

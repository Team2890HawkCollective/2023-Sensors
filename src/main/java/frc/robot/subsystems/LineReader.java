package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LineReader extends SubsystemBase{

private static DigitalInput Sensor2 = new DigitalInput(Constants.CHANNEL_2);
private static DigitalInput Sensor3 = new DigitalInput(Constants.CHANNEL_3);
private static DigitalInput Sensor4 = new DigitalInput(Constants.CHANNEL_4);

public static void isLine()
{
    System.out.println(Sensor2.get());
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

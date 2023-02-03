// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {



  private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);  
  private static RelativeEncoder m_Encoder = armMotor.getEncoder();
  private static boolean aPressed = false;
  private static boolean bPressed = false;
  private static boolean xPressed = false;
  private static boolean aFlag = false;
  private static boolean bFlag = false;
  private static boolean xFlag = false;

  public static void controlledMoveArm()
  {
    aPressed = driverController.getAButton();
    bPressed = driverController.getBButton();

    System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton() + " Encoder Value = " + Math.abs(m_Encoder.getPosition()) + " Motor Temp " + armMotor.getMotorTemperature());

    if(aPressed && Math.abs(m_Encoder.getPosition()) < 45) 
    {
      armMotor.set(Constants.POLARITY_SWAP * Constants.ARM_SPEED);
    } 
    else if(bPressed && Math.abs(m_Encoder.getPosition()) > 5) 
    {
      armMotor.set(Constants.ARM_SPEED);
    } 
    else 
    {
      armMotor.set(0.0);
    }
  }

  public static void threeButtonControl() // DOES NOT WORK CURRENTLY NEEDS FIXING
  {
    aPressed = driverController.getAButton();
    bPressed = driverController.getBButton();
    xPressed = driverController.getXButton();
    aFlag = driverController.getAButton();
    bFlag = driverController.getBButton();
    xFlag = driverController.getXButton();

    System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton() + " Encoder Value = " + Math.abs(m_Encoder.getPosition()) + 
     " Motor Temp " + armMotor.getMotorTemperature());

    

    if(aPressed && Math.abs(m_Encoder.getPosition()) < 45)
    {
      armMotor.set(Constants.POLARITY_SWAP * Constants.ARM_SPEED);
    }
    else if(bPressed && Math.abs(m_Encoder.getPosition()) > 5) 
    {
      armMotor.set(Constants.ARM_SPEED);
    } 
    else if(xPressed)
    {
      if (Math.abs(m_Encoder.getPosition()) > 20)
      {
        armMotor.set(Constants.ARM_SPEED);
      }
      else if(Math.abs(m_Encoder.getPosition()) < 24)
      {
        armMotor.set(Constants.POLARITY_SWAP * Constants.ARM_SPEED);
      }
    }
    else if(Math.abs(m_Encoder.getPosition()) > 45 || Math.abs(m_Encoder.getPosition()) < 0)
    { 
      armMotor.stopMotor();
    }
  }




  /** Creates a new ExampleSubsystem. */
  public Arm() {
    m_Encoder.setPosition(0);
    armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
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


  public static void PIDMoveArm()
  {
    aPressed = driverController.getAButton();
    bPressed = driverController.getBButton();
    xPressed = driverController.getXButton();

    System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton() + " Encoder Value = " + m_Encoder.getPosition() + " Motor Temp " + armMotor.getMotorTemperature());

    if(aPressed) 
    {
      //Setting the target position with the PID control
      armMotor.getPIDController().setReference(45, com.revrobotics.CANSparkMax.ControlType.kPosition);
    } 
    else if(bPressed) 
    {
      //Setting the target position with the PID control
      armMotor.getPIDController().setReference(5, com.revrobotics.CANSparkMax.ControlType.kPosition);
    } 

    else 
    {
      armMotor.set(0.0);
    }
    
  }


  public Arm() {
    m_Encoder.setPosition(0);
    armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);


    armMotor.getPIDController().setP(Constants.PID_P);
    armMotor.getPIDController().setI(Constants.PID_I);
    armMotor.getPIDController().setD(Constants.PID_D);

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
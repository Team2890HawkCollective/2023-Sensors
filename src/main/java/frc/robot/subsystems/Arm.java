// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.LinkedList;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends SubsystemBase {

  private static final String kGraphTitle = "Encoder Position";
  private static final int kMaxDataPoints = 100;

  //private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
  private static final CANSparkMax shoulderMotor = new CANSparkMax(Constants.SHOULDER_MOTOR, MotorType.kBrushless);

  //private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);  
  private static XboxController assistantController = new XboxController(Constants.ASSISTANT_XBOX_CONTROLLER_PORT);  

  //private static RelativeEncoder m_ExtensionEncoder = armMotor.getEncoder();
  private static RelativeEncoder m_ShoulderEncoder = shoulderMotor.getEncoder();


  private static boolean aPressed = false;
  private static boolean bPressed = false; 
  private static int dPadAngle;


  public static void ShoulderControl()
  {
    if(assistantController.getXButtonReleased()){
      //System.out.println("assistant driver controller x button, shoulder lowest");
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
    if(assistantController.getYButtonReleased()){
      //System.out.println("assistant driver controller y button, shoulder middle");
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
    if(assistantController.getBButtonReleased()){
      //System.out.println("assistant driver controller b button, shoulder highest");
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
  }

/*
  public static void PIDMoveArm()
  {
    //aPressed = driverController.getAButton();
    //bPressed = driverController.getBButton();
    
    //System.out.println("gets inside PID MOVE ARM ");

    
    double encoderPosition = m_Encoder.getPosition();
    SmartDashboard.putNumber("Encoder Position", encoderPosition);
    

    //System.out.println("gets passed dashboard placement ");


    armMotor.getPIDController().setP(SmartDashboard.getNumber("PID P", 0));
    armMotor.getPIDController().setI(SmartDashboard.getNumber("PID I", 0));
    armMotor.getPIDController().setD(SmartDashboard.getNumber("PID D", 0));
    armMotor.getPIDController().setFF(SmartDashboard.getNumber("PID FF", 0));
    armMotor.getPIDController().setIZone(SmartDashboard.getNumber("PID I Zone", 0));

    //System.out.println("gets passed pid placement ");

    //System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton() + " Encoder Value = " + m_Encoder.getPosition() + " Motor Temp " + armMotor.getMotorTemperature());
 
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
  */

  
  


  public Arm() {
    //m_ExtensionEncoder.setPosition(0);
    //armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

    m_ShoulderEncoder.setPosition(0);
    shoulderMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

    SmartDashboard.putNumber("PID P", Constants.PID_P);
    SmartDashboard.putNumber("PID I", Constants.PID_I);
    SmartDashboard.putNumber("PID D", Constants.PID_D);
    SmartDashboard.putNumber("PID FF", Constants.PID_FF);
    SmartDashboard.putNumber("PID I Zone", Constants.PID_I_ZONE);

    shoulderMotor.getPIDController().setP(Constants.ShPID_P);
    shoulderMotor.getPIDController().setI(Constants.ShPID_I);
    shoulderMotor.getPIDController().setD(Constants.ShPID_D);
    shoulderMotor.getPIDController().setD(Constants.ShPID_I_ZONE);
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
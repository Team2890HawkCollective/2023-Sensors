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

  private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
  private static final CANSparkMax shoulderMotor = new CANSparkMax(Constants.SHOULDER_MOTOR, MotorType.kBrushless);

  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);  
  private static XboxController assistController = new XboxController(Constants.ASSIST_XBOX_CONTROLLER_PORT);

  private static RelativeEncoder m_Encoder = armMotor.getEncoder();
  private static RelativeEncoder m_ShoulderEnc = shoulderMotor.getEncoder();

  private static boolean xPressed = false;
  private static boolean yPressed = false; 
  private static boolean bPressed = false;

  private static double encoderPosition;
  private static double encoderShoulderPosition;

  private static boolean leftBumper = false;
  private static boolean rightBumper = false;
  
  private static int dPadAngle;


  public static void ShoulderControl()
  {
    xPressed = assistController.getXButton();
    yPressed = assistController.getYButton();
    bPressed = assistController.getBButton();

    encoderShoulderPosition = m_ShoulderEnc.getPosition();

    if(xPressed){
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
    else if(yPressed){
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
    else if(bPressed){
      shoulderMotor.getPIDController().setReference(0, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }
    else{
      shoulderMotor.set(0);
    }
  }

  public static void GrabberControl(){
    leftBumper = assistController.getLeftBumperReleased();
    rightBumper = assistController.getRightBumperReleased();

    /* 
     * 
     * 
     *  TODO FINISH IF GRABBER IS BUILT
     * 
     * 
     */
    
  }





  public static void PIDMoveArm()
  {

    dPadAngle = assistController.getPOV();
    
    //System.out.println("gets inside PID MOVE ARM ");

    
    encoderPosition = m_Encoder.getPosition();
    SmartDashboard.putNumber("Encoder Position", encoderPosition);
    

    //System.out.println("gets passed dashboard placement ");


    armMotor.getPIDController().setP(SmartDashboard.getNumber("PID P", 0));
    armMotor.getPIDController().setI(SmartDashboard.getNumber("PID I", 0));
    armMotor.getPIDController().setD(SmartDashboard.getNumber("PID D", 0));
    armMotor.getPIDController().setFF(SmartDashboard.getNumber("PID FF", 0));
    armMotor.getPIDController().setIZone(SmartDashboard.getNumber("PID I Zone", 0));

    //System.out.println("gets passed pid placement ");

    //System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton() + " Encoder Value = " + m_Encoder.getPosition() + " Motor Temp " + armMotor.getMotorTemperature());
 
    if(dPadAngle > 10 && dPadAngle < 170) 
    {
     // System.out.println("gets passed AAAAAAAAa ");

      //Setting the target position with the PID control
      armMotor.getPIDController().setReference(45, com.revrobotics.CANSparkMax.ControlType.kPosition);
   
    } 
    else if(dPadAngle < 350 && dPadAngle > 190) 
    {
     // System.out.println("gets passed BBBBBBBBb ");

      //Setting the target position with the PID control
      armMotor.getPIDController().setReference(5, com.revrobotics.CANSparkMax.ControlType.kPosition);
    } 

    else 
    {
      //System.out.println("neither pressed ");

      armMotor.set(0.0);
    }
  }

  
  


  public Arm() {
    m_Encoder.setPosition(0);
    m_ShoulderEnc.setPosition(0);
    armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

    SmartDashboard.putNumber("PID P", Constants.PID_P);
    SmartDashboard.putNumber("PID I", Constants.PID_I);
    SmartDashboard.putNumber("PID D", Constants.PID_D);
    SmartDashboard.putNumber("PID FF", Constants.PID_FF);
    SmartDashboard.putNumber("PID I Zone", Constants.PID_I_ZONE);


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
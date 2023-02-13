// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {



  private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);

  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);  

  private static RelativeEncoder m_Encoder = armMotor.getEncoder();
  private static boolean aPressed = false;
  private static boolean bPressed = false;



  public static void PIDMoveArm()
  {
    aPressed = driverController.getAButton();
    bPressed = driverController.getBButton();
    armMotor.getPIDController().setP(Constants.PID_P);
    armMotor.getPIDController().setI(Constants.PID_I);
    armMotor.getPIDController().setD(Constants.PID_D);
    armMotor.getPIDController().setFF(Constants.PID_FF);
    armMotor.getPIDController().setIZone(Constants.PID_I_ZONE);

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


    
    EncoderGraph.data.add(new EncoderData(System.currentTimeMillis(), m_Encoder.getPosition()));
  }


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
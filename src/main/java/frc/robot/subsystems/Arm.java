// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

public class Arm extends SubsystemBase {



  private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
  

  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  
  private static RelativeEncoder m_Encoder = armMotor.getEncoder();
  private static boolean aPressed = false;
  private static boolean bPressed = false;

  public static void moveArm()
  {
    aPressed = driverController.getAButton();
    bPressed = driverController.getBButton();

    System.out.println("A = " + driverController.getAButton() + " B = " + driverController.getBButton());

    if(driverController.getAButton())
    {
        armMotor.set(Constants.ARM_SPEED);
    }
    else if (driverController.getBButton())
    {
        armMotor.set(-1 * Constants.ARM_SPEED);
    }
    else if (!driverController.getAButton() && !driverController.getBButton())
    {
        armMotor.stopMotor();
    }
    // if(aPressed)
    // {
    //     //System.out.println("A Pressed Encoder " + m_Encoder.getPosition());
    //     //armMotor.set(Constants.ARM_SPEED);

    //     System.out.println("A? = " + driverController.getAButton());
    // }

    // if(bPressed)
    // {
    //     //System.out.println("B Pressed Encoder " + m_Encoder.getPosition());
    //     //armMotor.set(-1 * Constants.ARM_SPEED);

    //     System.out.println("A? = " + driverController.getBButton());
    // }

    // aPressed = false;
    // bPressed = false;
    // armMotor.stopMotor();

    // System.out.println(driverController.getRightX());
    // System.out.println(driverController.getRightY());

    //FUNCTIONAL MECANUM BASE
    // FrontLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    // BackLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD));

    // FrontRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    // BackRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD));

  }



  /** Creates a new ExampleSubsystem. */
  public Arm() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
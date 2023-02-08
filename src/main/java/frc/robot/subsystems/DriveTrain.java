// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain extends SubsystemBase {




  private static MotorController frontLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_LEFT);
  private static MotorController frontRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_RIGHT);
  private static MotorController backLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_LEFT);
  private static MotorController backRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_RIGHT);

  private static double[] motorCoefficients = {1.0, 1.0, 1.0, 1.0};

  private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftVictorSPX, backLeftVictorSPX, frontRightVictorSPX, backRightVictorSPX);


  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static double xInput;

  private static double yInput;

  private static double rInput;


  public static void driveMotor()
  {
    backLeftVictorSPX.setInverted(true);
    backRightVictorSPX.setInverted(true);
    frontLeftVictorSPX.setInverted(false);
    frontRightVictorSPX.setInverted(false);

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .02));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .02));
    rInput = (MathUtil.applyDeadband(driverController.getRightY(), .02));
    //System.out.println("X = " + xInput);
    //System.out.println("Y = " + yInput);


    //2/8/2023 USE THE new Rotation2d() THING TO PASS A BLANK GYRO VALUE IF NOT USING GYRO !!!!!!!!!!!!!!

    //1/24/2023 Inverting the X joystick
    chassisDrive.driveCartesian
          (driverController.getLeftX() * -1 * Constants.SPEED_MOD, 
          driverController.getLeftY() * Constants.SPEED_MOD, 
          driverController.getRightX() * -1 * Constants.SPEED_MOD,
          new Rotation2d(), 
          motorCoefficients);
    // System.out.println(driverController.getRightX());
    // System.out.println(driverController.getRightY());

    //FUNCTIONAL MECANUM BASE
    // FrontLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    // BackLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD));

    // FrontRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    // BackRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD));

  }



  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    
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
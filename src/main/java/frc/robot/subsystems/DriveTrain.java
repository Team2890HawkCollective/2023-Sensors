// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {




  private static MotorController frontLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_LEFT);
  private static MotorController frontRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_RIGHT);
  private static MotorController backLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_LEFT);
  private static MotorController backRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_RIGHT);

  private static double[] motorCoefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};

  private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftVictorSPX, backLeftVictorSPX, frontRightVictorSPX, backRightVictorSPX);


  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static double xInput;

  private static double yInput;

  private static double rInput;
  private static boolean isMecanum = true;

  private static DoubleSolenoid butterFlySolenoid = null;

  public static void updateShuffleboard()
  {
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);
  }



  public static void chooseDrive()
  {
    if (driverController.getLeftBumper())
    {
      isMecanum = !isMecanum;

      if(isMecanum){/*Extend*/}
      if(!isMecanum){/*Retract*/}

    }
    if (isMecanum)
    {
      driveMecanum();
    }
    else
    {
      driveFriction();
    }
  }


  /**
   * Drives the robot using mecanum drive
   */
  public static void driveMecanum()
  {
    backLeftVictorSPX.setInverted(true);
    backRightVictorSPX.setInverted(true);
    frontLeftVictorSPX.setInverted(false);
    frontRightVictorSPX.setInverted(false);

    xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .02));
    yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .02));
    rInput = (MathUtil.applyDeadband(driverController.getRightY(), .02));

    //2/8/2023 USE THE new Rotation2d() THING TO PASS A BLANK GYRO VALUE IF NOT USING GYRO !!!!!!!!!!!!!!

    chassisDrive.driveCartesian
          (driverController.getLeftX() * -1 * Constants.SPEED_MOD, 
          driverController.getLeftY() * Constants.SPEED_MOD, 
          driverController.getRightX() * -1 * Constants.SPEED_MOD,
          new Rotation2d(), 
          motorCoefficients);
  }

  /**
   * This is the drive method for the non-mecanum drive train
   */
  public static void driveFriction()
  {
    backLeftVictorSPX.setInverted(true);
    backRightVictorSPX.setInverted(true);
    frontLeftVictorSPX.setInverted(false);
    frontRightVictorSPX.setInverted(false);

    xInput = 0;
    yInput = (MathUtil.applyDeadband(driverController.getLeftX(), .02));
    rInput = 0;
    


    chassisDrive.driveCartesian
          (driverController.getLeftX() * -1 * Constants.SPEED_MOD, 
          0, 
          0,
          new Rotation2d(), 
          motorCoefficients);
  }



  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    SmartDashboard.putNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    SmartDashboard.putNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    SmartDashboard.putNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    SmartDashboard.putNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);
    
    butterFlySolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH , Constants.BUTTERFLY_SOLENOID_DEPLOY, Constants.BUTTERFLY_SOLENOID_RETRACT);
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
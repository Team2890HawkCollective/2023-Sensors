// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.time.format.ResolverStyle;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {




  // private static MotorController frontLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_LEFT);
  // private static MotorController frontRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_FRONT_RIGHT);
  // private static MotorController backLeftVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_LEFT);
  // private static MotorController backRightVictorSPX = new VictorSPXMecanum(Constants.MOTOR_BACK_RIGHT);

  // private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  // private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  // private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  // private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);



  private static double[] motorCoefficients = {Constants.frontLeftMotorCoeff, Constants.frontRightMotorCoeff, Constants.backLeftMotorCoeff, Constants.backRightMotorCoeff};

  // private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftVictorSPX, backLeftVictorSPX, frontRightVictorSPX, backRightVictorSPX);
  
  //private static MecanumWrapperClass chassisDrive = new MecanumWrapperClass(frontLeftSparkMax, backLeftSparkMax, frontRightSparkMax, backRightSparkMax);


  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static double xInput;

  private static double yInput;

  private static double rInput;
  private static boolean isMecanum = false;

  //private static DoubleSolenoid butterFlySolenoid = null;
  //private static Compressor phCompressor = null;

  public static void updateShuffleboard()
  {
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);

    //SmartDashboard.putNumber("Compressor Pressure", phCompressor.getPressure());
  }





  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    SmartDashboard.putNumber("frontLeftMotorCoeff", Constants.frontLeftMotorCoeff);
    SmartDashboard.putNumber("frontRightMotorCoeff", Constants.frontRightMotorCoeff);
    SmartDashboard.putNumber("backLeftMotorCoeff", Constants.backLeftMotorCoeff);
    SmartDashboard.putNumber("backRightMotorCoeff", Constants.backRightMotorCoeff);
    
    // butterFlySolenoid = new DoubleSolenoid(11, PneumaticsModuleType.REVPH , Constants.BUTTERFLY_SOLENOID_DEPLOY, Constants.BUTTERFLY_SOLENOID_RETRACT);
    // phCompressor = new Compressor(11, PneumaticsModuleType.REVPH);
    // phCompressor.enableAnalog(90, 110);
    // butterFlySolenoid.set(Value.kReverse);
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
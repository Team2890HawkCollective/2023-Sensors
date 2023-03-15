// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private static CANSparkMax frontLeftSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_LEFT, MotorType.kBrushless);
  private static CANSparkMax frontRightSparkMax = new CANSparkMax(Constants.MOTOR_FRONT_RIGHT, MotorType.kBrushless);
  private static CANSparkMax backLeftSparkMax = new CANSparkMax(Constants.MOTOR_BACK_LEFT, MotorType.kBrushless);
  private static CANSparkMax backRightSparkMax = new CANSparkMax(Constants.MOTOR_BACK_RIGHT, MotorType.kBrushless);
  
  private static XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  private static AHRS gyro = new AHRS();

  private static int[] motorPolarity = {Constants.FRONT_LEFT_POLARITY, Constants.FRONT_RIGHT_POLARITY, Constants.BACK_LEFT_POLARITY, Constants.BACK_RIGHT_POLARITY};
  private static double[] motorCoefficients = {Constants.FRONT_LEFT_COEFF, Constants.FRONT_RIGHT_COEFF, Constants.BACK_LEFT_COEFF, Constants.BACK_RIGHT_COEFF};
  private static double[] gyroDeltas = new double[] {0,0,0};
  private static double[] gyroAngles = new double[] {0,0,0};
  private static boolean holdHeading = false;
  private static double heading = 0.0;
  
  public DriveTrain() {
    
  }

  public static void drive(){
    double[] axies = readControllerAxies(driverController);
    boolean rbPressed = driverController.getRightBumper();
    boolean lbPressed = driverController.getLeftBumper();

    if(axies[0] != 0 && axies[1] != 0){
      holdHeading = true;
      diagonal(axies[0], axies[1]);
    } else if(axies[0] != 0) {
      holdHeading = true;
      strafe(axies[0]);
    } else if(axies[1] != 0) {
      holdHeading = true;
      straight(axies[1]);
    } else if(axies[3] != 0) {
      holdHeading = false;
      twist(axies[3]);
    } else if(rbPressed && !lbPressed) {
      holdHeading = false;
      corner(false, true);
    } else if (lbPressed && !rbPressed) {
      holdHeading = false;
      corner(true, false);
    } else {
      holdHeading = false;
      stopMotors();
    }

    if(!holdHeading){
      heading = gyro.getAngle();
    }
  }

  public static void update()
  {
    motorCoefficients[0] = SmartDashboard.getNumber("frontLeftMotorCoeff", motorCoefficients[0]);
    motorCoefficients[1] = SmartDashboard.getNumber("frontRightMotorCoeff", motorCoefficients[1]);
    motorCoefficients[2] = SmartDashboard.getNumber("backLeftMotorCoeff", motorCoefficients[2]);
    motorCoefficients[3] = SmartDashboard.getNumber("backRightMotorCoeff", motorCoefficients[3]);
    gyroDeltas = readGyroDisplacement();
    gyroAngles = readGyroRot();
  }

  public static double[] readGyroDisplacement() {
    double[] arr = {gyro.getDisplacementX(), gyro.getDisplacementY(), gyro.getDisplacementZ()};
    return arr;
  }

  public static double[] readGyroRot() {
    double[] arr = {gyro.getRoll(), gyro.getPitch(), gyro.getYaw()};
    return arr;
  }

  public static void stopMotors()
  {
    frontLeftSparkMax.set(0.0);
    frontRightSparkMax.set(0.0);
    backLeftSparkMax.set(0.0);
    backRightSparkMax.set(0.0);
  }

  // Y-Axis on drive controller left joystick
  public static void straight(double inputY){
    double e = heading - gyro.getAngle();
    double[] coeff = motorCoefficients;

    if(e >= 15){
      e = Math.abs(e) / 100;
      coeff = new double[] {1.0 - e, 1.0 + e, 1.0 - e, 1.0 + e};
    } else if (e <= -15){
      e = Math.abs(e) / 100;
      coeff = new double[] {1.0 + e, 1.0 - e, 1.0 + e, 1.0 - e};
    }

    double[] baseBehaviour = new double[] {1.0 * inputY, 1.0 * inputY, 1.0 * inputY, 1.0 * inputY};
    double[] motorInputs = applyFilters(motorPolarity, coeff, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }

  // X-Axis on drive controller left joystick
  public static void strafe(double inputX){
    double[] baseBehaviour = new double[] {1.0 * inputX, -1.0 * inputX, -1.0 * inputX, 1.0 * inputX};
    double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }

  // Quadrants on drive controller left joystick
  public static void diagonal(double inputX, double inputY){
    double scale = (inputX + inputY)/2;
    double[] baseBehaviour = new double[] {0,0,0,0};

    if(inputX > 0 && inputY > 0){
      baseBehaviour = new double[] {1.0 * scale, 0.0, 0.0, 1.0 * scale};
    } else if (inputX > 0 && inputY < 0){
      baseBehaviour = new double[] {0.0, -1.0 * scale, -1.0 * scale, 0.0};
    } else if (inputX < 0 && inputY > 0){
      baseBehaviour = new double[] {0.0, 1.0 * scale, 1.0 * scale, 0.0};
    } else {
      baseBehaviour = new double[] {-1.0 * scale, 0.0, 0.0, -1.0 * scale};
    }

    double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }

  // X-Axis on drive controller right joystick
  public static void twist(double inputX){
    double[] baseBehaviour = new double[] {1.0 * inputX, -1.0 * inputX, 1.0 * inputX, -1.0 * inputX};
    double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }

  // RB and LB on drive controller
  public static void corner(boolean left, boolean right){
    double[] baseBehaviour = {0,0,0,0};

    if(left && right) {
      baseBehaviour = new double[] {0,0,0,0};
    } else if(right) {
      baseBehaviour = new double[] {1.0, 0, 1.0, 0};
    } else {
      baseBehaviour = new double[] {0, 1.0, 0, 1.0};
    }

    double[] motorInputs = applyFilters(motorPolarity, motorCoefficients, baseBehaviour);
    frontLeftSparkMax.set(motorInputs[0]);
    frontRightSparkMax.set(motorInputs[1]);
    backLeftSparkMax.set(motorInputs[2]);
    backRightSparkMax.set(motorInputs[3]);
  }

  public static double[] readControllerAxies(XboxController xbox){
    double x = MathUtil.applyDeadband(xbox.getLeftX(), Constants.DEADBAND);
    double y = MathUtil.applyDeadband(xbox.getLeftY(), Constants.DEADBAND);
    double r = MathUtil.applyDeadband(xbox.getRightX(), Constants.DEADBAND);
    double[] arr = {x, y, r};
    return arr;
  }

  public static double[] applyFilters(int[] polarity, double[] coeff, double[] speeds){
    double[] correctedValues = new double[] {
      coeff[0] * speeds[0] * polarity[0],
      coeff[1] * speeds[1] * polarity[1],
      coeff[2] * speeds[2] * polarity[2], 
      coeff[3] * speeds[3] * polarity[3]
    };
    return correctedValues;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    update();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    update();
  }
}
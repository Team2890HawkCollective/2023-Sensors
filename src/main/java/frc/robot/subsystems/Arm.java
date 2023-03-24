// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends SubsystemBase {

  private static final String kGraphTitle = "Encoder Position";
  private static final int kMaxDataPoints = 100;

  private static final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR, MotorType.kBrushless);
  private static final CANSparkMax shoulderMotor = new CANSparkMax(Constants.SHOULDER_MOTOR, MotorType.kBrushless);

  private static RelativeEncoder m_Encoder = armMotor.getEncoder();
  private static RelativeEncoder m_ShoulderEnc = shoulderMotor.getEncoder();

  private static boolean xPressed = false;
  private static boolean yPressed = false;
  private static boolean bPressed = false;
  private static boolean aPressed = false;


  private static double encoderPosition;
  private static double encoderShoulderPosition;

  private static boolean leftBumper = false;
  private static boolean rightBumper = false;
  private static DoubleSolenoid GrabberSolenoid = null;

  private static XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  private static XboxController assistantController = new XboxController(Constants.ASSISTANT_CONTROLLER_PORT);

  private static Joystick arcadeJoystick1;
  private static Joystick arcadeJoystick2;

  private static JoystickButton[] arcadeJoystick1Buttons;
  private static JoystickButton[] arcadeJoystick2Buttons;

  private static int dPadAngle;

  private static double yAssistantValue;
  private static boolean closeButton = false;
  private static boolean openButton = false;



  public static void GrabberControl() {

    boolean leftBumper = assistantController.getLeftBumper();
    boolean rightBumper = assistantController.getRightBumper();

    if (rightBumper) {
      GrabberSolenoid.set(Value.kForward);
    } else if (leftBumper) {
      GrabberSolenoid.set(Value.kReverse);
    }

  }

  public static void PIDMoveArm() {
    yAssistantValue = assistantController.getLeftY();
    yPressed = assistantController.getYButton();
    aPressed = assistantController.getAButton();

    encoderPosition = m_Encoder.getPosition();
    SmartDashboard.putNumber("Encoder Position", encoderPosition);

    armMotor.getPIDController().setP(SmartDashboard.getNumber("PID P", 0));
    armMotor.getPIDController().setI(SmartDashboard.getNumber("PID I", 0));
    armMotor.getPIDController().setD(SmartDashboard.getNumber("PID D", 0));
    armMotor.getPIDController().setFF(SmartDashboard.getNumber("PID FF", 0));
    armMotor.getPIDController().setIZone(SmartDashboard.getNumber("PID I Zone", 0));

    // if (yAssistantValue < -0.2) {
    if (yPressed) {
      armMotor.set(.3);
      //armMotor.getPIDController().setReference(175, com.revrobotics.CANSparkMax.ControlType.kPosition);
    } //else if (yAssistantValue > 0.2) {
      if (aPressed) {
      armMotor.set(-.3);
      //armMotor.getPIDController().setReference(-5, com.revrobotics.CANSparkMax.ControlType.kPosition);
    } else {
      armMotor.set(0.0);
    }
  }

  public Arm() {
    m_Encoder.setPosition(0);
    m_ShoulderEnc.setPosition(0);
    armMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    shoulderMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    shoulderMotor.follow(armMotor, true);    

    SmartDashboard.putNumber("PID P", Constants.PID_P);
    SmartDashboard.putNumber("PID I", Constants.PID_I);
    SmartDashboard.putNumber("PID D", Constants.PID_D);
    SmartDashboard.putNumber("PID FF", Constants.PID_FF);
    SmartDashboard.putNumber("PID I Zone", Constants.PID_I_ZONE);

    GrabberSolenoid = new DoubleSolenoid(11, PneumaticsModuleType.REVPH,
        Constants.GRABBER_SOLENOID_DEPLOY, Constants.GRABBER_SOLENOID_RETRACT);





    GrabberSolenoid.set(Value.kForward);
    arcadeJoystick1 = new Joystick(2);
    arcadeJoystick2 = new Joystick(3);

    int numberOfButtons = 10;
    arcadeJoystick1Buttons = new JoystickButton[numberOfButtons];
    arcadeJoystick2Buttons = new JoystickButton[numberOfButtons];

    for (int i = 0; i < numberOfButtons; i++) {
      arcadeJoystick1Buttons[i] = new JoystickButton(arcadeJoystick1, i + 1);
      arcadeJoystick2Buttons[i] = new JoystickButton(arcadeJoystick2, i + 1);
    }
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
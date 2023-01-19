// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain extends SubsystemBase {


  private VictorSPX FrontLeftVictor = new VictorSPX(Constants.MOTOR_FRONT_LEFT);
  private VictorSPX FrontRightVictor = new VictorSPX(Constants.MOTOR_FRONT_RIGHT);
  private VictorSPX BackLeftVictor = new VictorSPX(Constants.MOTOR_BACK_LEFT);
  private VictorSPX BackRightVictor = new VictorSPX(Constants.MOTOR_BACK_RIGHT);
  private MecanumDrive ChassisDrive = new MecanumDrive((MotorController)FrontLeftVictor, (MotorController)BackRightVictor, (MotorController)FrontRightVictor, (MotorController)BackRightVictor);


  private XboxController driverController = new XboxController(Constants.DRIVER_XBOX_CONTROLLER_PORT);

  double xInput = (MathUtil.applyDeadband(driverController.getLeftX(), .02));

  double yInput = -(MathUtil.applyDeadband(driverController.getLeftY(), .02));


  public void DriveMotor(double xInput, double yInput)
  {
    FrontLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    BackLeftVictor.set(ControlMode.PercentOutput, ((xInput + yInput) * Constants.SPEED_MOD));

    FrontRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD * Constants.POLARITY_SWAP));
    BackRightVictor.set(ControlMode.PercentOutput, ((xInput - yInput) * Constants.SPEED_MOD));


  }



  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.Arm;


/** An example command that uses an example subsystem. */
public class AutoCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_subsystem;
  Timer timer;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public AutoCommand(DriveTrain subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    DriveTrain.armDeployDoubleSolenoid.set(Value.kForward);
  }

  @Override
  public void execute() {

    // ramp();
    // rampTime();
    lineTime();
    // lineEncoder();
    System.out.println("AUTO EXECUTE METHOD");
  }

  private static void lineTime() {
    System.out.print("LINE METHOD");
    DriveTrain.driveForward();
  }

  private static void rampTime() {
    DriveTrain.driveForward();
    Timer.delay(5);
    DriveTrain.stopMotors();
    Timer.delay(3000);
  }

  private static void lineEncoder() {
    double encoderArray[] = DriveTrain.getEncoderArray();
    double encoderAverage = (encoderArray[0] + encoderArray[1] + encoderArray[2] + encoderArray[3]) / 4;
    if (encoderAverage < Constants.LINE_TARGET_DISTANCE) {
      DriveTrain.driveForward();
    } else {
      DriveTrain.stopMotors();
    }
  }

  private static void ramp() {
    if (DriveTrain.getDeltaZ() > Constants.RAMP_TARGET_HEIGHT) {
      DriveTrain.stopMotors();
    } else {
      DriveTrain.driveForward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DriveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > 3;
    // return false;
  }
}
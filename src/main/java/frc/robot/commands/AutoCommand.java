// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Auto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
/** An example command that uses an example subsystem. */
public class AutoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Auto m_subsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */



  public AutoCommand(Auto subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute(){

    //ramp();
    //rampTime();
    lineTime();
    //lineEncoder();
  }

    private static void lineTime(){
      DriveTrain.driveForward();
      Timer.delay(5);
      DriveTrain.stopMotors();
      Timer.delay(1500);
    }

    private static void rampTime(){
      DriveTrain.driveForward();
      Timer.delay(5);
      DriveTrain.stopMotors();
      Timer.delay(3000);
    }

    private static void lineEncoder(){
      double encoderArray[] = DriveTrain.getEncoderArray();
      double encoderAverage = (encoderArray[0] + encoderArray[1] + encoderArray[2] + encoderArray[3] )/4;
      if(encoderAverage < Constants.LINE_TARGET_DISTANCE){
        DriveTrain.driveForward();
      }
      else{
        DriveTrain.stopMotors();
      }
    }
    private static void ramp(){
        if(DriveTrain.getDeltaZ() > Constants.RAMP_TARGET_HEIGHT){
            DriveTrain.stopMotors();
        }
        else{
            DriveTrain.driveForward();
        }
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.package frc.robot.subsystems;
package frc.robot.subsystems;

import frc.robot.Constants;

/*1/20/23 created the new subsystem VisualSensors. Copied most of the info from the DriveTrain subsystem
Imported NetworkTable, NetworkTableEntry, and NetworkTableInstance b/c the limelight website told me too*/
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
public class VisualSensors extends SubsystemBase
{
 /**
   * Calls the limelight and gather basic data
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry limelightX = table.getEntry("tx"); //tx
  NetworkTableEntry limelightArea = table.getEntry("ta"); //ta
  NetworkTableEntry limelightTargetFound = table.getEntry("tv"); //tv

    private boolean targetingOkay = true;
    
    public void target()
    {
      //Gathers data 
        final double limelightXValue = limelightX.getDouble(0.0); //tx  0.0 is default value
        final double limelightAreaValue = limelightArea.getDouble(0.0); //ta
        final double limelightTargetFoundValue = limelightTargetFound.getDouble(0.0); //tv
        System.out.println("distance is: "+limelightXValue);
        System.out.println("area is: "+limelightAreaValue);
        System.out.println("target is: "+limelightTargetFoundValue);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      
    }
}

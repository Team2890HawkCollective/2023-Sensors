package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;


class VictorSPXMecanum extends VictorSPX implements MotorController{

    double speed;
    boolean isInverted;


    public VictorSPXMecanum(int deviceNumber)
    {
       super(deviceNumber);
    }
    

   /**
   * Common interface for setting the speed of a motor controller.
   *
   * @param speed The speed to set. Value should be between -1.0 and 1.0.
   */
  public void set(double speed)
  {
    if(isInverted)
    {
        super.set(ControlMode.PercentOutput, speed * -1);

    }
    else
    {
        super.set(ControlMode.PercentOutput, speed); 
    }
    this.speed = speed;
  }

  /**
   * Sets the voltage output of the MotorController. Compensates for the current bus voltage to
   * ensure that the desired voltage is output even if the battery voltage is below 12V - highly
   * useful when the voltage outputs are "meaningful" (e.g. they come from a feedforward
   * calculation).
   *
   * <p>NOTE: This function *must* be called regularly in order for voltage compensation to work
   * properly - unlike the ordinary set function, it is not "set it and forget it."
   *
   * @param outputVolts The voltage to output.
   */
  public void setVoltage(double outputVolts) {
  //  set(outputVolts / RobotController.getBatteryVoltage());
  }

  /**
   * Common interface for getting the current set speed of a motor controller.
   *
   * @return The current set speed. Value is between -1.0 and 1.0.
   */
  public double get()
  {
    return this.speed;
  }

  /**
   * Common interface for inverting direction of a motor controller.
   *
   * @param isInverted The state of inversion true is inverted.
   */
  public void setInverted(boolean isInverted)
  {
    this.isInverted = isInverted;
  }

  /**
   * Common interface for returning if a motor controller is in the inverted state or not.
   *
   * @return isInverted The state of the inversion true is inverted.
   */
  public boolean getInverted()
  {
    return this.isInverted;
  }

  /** Disable the motor controller. */
  public void disable()
  {
    this.speed = 0;
  }

  /**
   * Stops motor movement. Motor can be moved again by calling set without having to re-enable the
   * motor.
   */
  public void stopMotor()
  {
    this.speed = 0;
  }




}
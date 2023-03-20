package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DualSparkMax {
    private CANSparkMax motor1;
    private CANSparkMax motor2;

    public DualSparkMax(int motor1ID, int motor2ID, MotorType motorType) {
        motor1 = new CANSparkMax(motor1ID, motorType);
        motor2 = new CANSparkMax(motor2ID, motorType);

        motor2.follow(motor1, true); // true means invert the motor
    }

    public void set(double speed) {
        motor1.set(speed);
    }

    public double get() {
        return motor1.get();
    }

    public void setIdleMode(IdleMode idleMode) {
        motor1.setIdleMode(idleMode);
        motor2.setIdleMode(idleMode);
    }

    public void setSmartMotionVelocity(double velocity, int slotID) {
        motor1.getPIDController().setSmartMotionMaxVelocity(velocity, slotID);
        motor2.getPIDController().setSmartMotionMaxVelocity(velocity, slotID);
    }

    public void setCurrentLimit(int limit) {
        motor1.setSmartCurrentLimit(limit);
        motor2.setSmartCurrentLimit(limit);
    }

    public void setSmartMotionAcceleration(double acceleration, int slotID) {
        motor1.getPIDController().setSmartMotionMaxAccel(acceleration, slotID);
        motor2.getPIDController().setSmartMotionMaxAccel(acceleration, slotID);
    }

    public void setSoftLimit(float limit, boolean forward) {
        if (forward) {
            motor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, limit);
            motor2.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, limit);
        } else {
            motor1.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, limit);
            motor2.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, limit);
        }
    }

    public void enableSoftLimit(boolean forward, boolean enable) {
        if (forward) {
            motor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, enable);
            motor2.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, enable);
        } else {
            motor1.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, enable);
            motor2.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, enable);
        }
    }

    // Add any other methods you may need from the SparkMax class
}

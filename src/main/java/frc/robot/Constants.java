// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int MOTOR_FRONT_LEFT = 1;
    public static final int MOTOR_FRONT_RIGHT = 2;
    public static final int MOTOR_BACK_LEFT = 3;
    public static final int MOTOR_BACK_RIGHT = 4;

    public static final int DRIVER_XBOX_CONTROLLER_PORT = 0;

    public static final double SPEED_MOD = 1;

    public static final int POLARITY_SWAP = -1;

    /*1/20/2023, added final vairables CAMERA_PORT and LIMELIGHT_PORT
    given temporary values of 1 and 2 respectively b/c i dont know what port they will end up being in*/
    public static final int CAMERA_PORT=1;
    public static final int LIMELIGHT_PORT=2;
    /**
     * Distances and area calculations used for limelight targeting
     */
    public static final double LIMELIGHT_TARGET_FOUND = 1.0; //tv
    public static final double LIMELIGHT_X_RANGE_MAXIMUM = 1.0; //range for which limelight is considered centered
    public static final double LIMELIGHT_AREA_FOUND_MINIMUM = 1.0; //minimum area for target to be considered found
    public static final double LIMELIGHT_AREA_FOUND_MAXIMUM = 2.5; //maximum area where target is reachable
    public static final double LIMELIGHT_TARGETING_AREA_LARGE_VALUE = 2.7; //11-15 ft away ta value
    public static final double LIMELIGHT_TARGETING_AREA_MEDIUM_VALUE = 3.0; //9-11 ft away ta value
    public static final double LIMELIGHT_TARGETING_AREA_SMALL_VALUE = 3.2; //7-9 ft awat ta value
    public static final double SHOOTER_SPEED_LIMELIGHT_TARGETING_AREA_LARGE_VALUE = 0.8; //11-15 ft shooter speed
    public static final double SHOOTER_SPEED_LIMELIGHT_TARGETING_AREA_MEDIUM_VALUE = 0.7; //9-11 ft shooter speed
    public static final double SHOOTER_SPEED_LIMELIGHT_TARGETING_AREA_SMALL_VALUE = 0.6; //7-9 ft shooter speed



}
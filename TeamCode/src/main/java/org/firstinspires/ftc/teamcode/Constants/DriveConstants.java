package org.firstinspires.ftc.teamcode.Constants;

import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Constants.Enums.DriveType;
import org.firstinspires.ftc.teamcode.Localizer.Main.Encoder;

public class DriveConstants {
    public static final DriveType driveType = DriveType.TANK;

    // ==== MECANUM ====
    public static String leftFrontName = "LeftFront";
    public static String leftBackName = "LeftBack";
    public static String rightFrontName = "RightFront";
    public static String rightBackName = "RightBack";

//    ==== TANK ====
    public static String leftName = "left";
    public static String rightName = "right";

//    ==== ENCODER DIRECTION ====
        // === MECANUM DIRECTION
    public static int LeftFrontDirection = Encoder.FORWARD;
    public static int LeftBackDirection = Encoder.REVERSE;
    public static int RightFrontDirection = Encoder.FORWARD;
    public static int RightBackDirection = Encoder.REVERSE;
        // === TANK DIRECTION
    public static int LeftDirection = Encoder.FORWARD;
    public static int Rightdirection = Encoder.REVERSE;

    // NUMBER
    public static double wheelDiameter = 3.54331;
    public static double ticksPerRev = 537.7;
    public static double gearRatio = 0.0;

    public static double ForwardMultiplier = 1.0;
    public static double LateralMultiplier = 1.0;
    public static double HeadingMultiplier = 1.0;
//    Both in Inches
    public static double TrackWidth = 12.5; //  // LeftWheel to Right Wheel
    public static double WheelBase = 12.5; // Back Wheel to Front Wheel

//    === PINPOINT ===
    public static String pinpointName = "odo";
}

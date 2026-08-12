package org.firstinspires.ftc.teamcode.Localizer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.Constants.Enums.DriveType;
import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;
import org.firstinspires.ftc.teamcode.Localizer.Main.Encoder;

public class DriveEncoderLocalizer implements Localizer {
    private final DriveType type;
    private Encoder left, right; // TANK
    private Encoder leftFront, leftBack, rightFront, rightBack; // MECANUM

    private Pose2d pose = new Pose2d(0, 0, new Rotation2d(0));
    private Pose2d velocity = new Pose2d(0, 0, new Rotation2d(0));
    private Long previousNanoTime;

    public DriveEncoderLocalizer(HardwareMap map) {
        this.type = DriveConstants.driveType;

        if (type == DriveType.MECANUM) {
            leftFront =
                    new Encoder(map.get(MotorEx.class, DriveConstants.leftFrontName)).setDirection(DriveConstants.LeftFrontDirection);
            rightFront =
                    new Encoder(map.get(MotorEx.class, DriveConstants.rightFrontName)).setDirection(DriveConstants.RightFrontDirection);
            leftBack =
                    new Encoder(map.get(MotorEx.class, DriveConstants.leftBackName)).setDirection(DriveConstants.LeftBackDirection);
            rightBack =
                    new Encoder(map.get(MotorEx.class, DriveConstants.rightBackName)).setDirection(DriveConstants.RightBackDirection);
        } else {
            left = new Encoder(map.get(MotorEx.class, DriveConstants.leftName)).setDirection(DriveConstants.LeftDirection);
            right = new Encoder(map.get(MotorEx.class, DriveConstants.rightName)).setDirection(DriveConstants.Rightdirection);
        } previousNanoTime = System.nanoTime();
    }

    private double ticksToInch(double ticks) {
        double circum = Math.PI * DriveConstants.wheelDiameter;
        return (ticks / DriveConstants.ticksPerRev) * DriveConstants.gearRatio * circum;
    }

    @Override
    public void update() {
        Long now = System.nanoTime(); double dt = Math.max((now - previousNanoTime) / 1e9, 1e-6);
        previousNanoTime = now;

        double dx, dy, dheading; if (type == DriveType.MECANUM) {
            double dLF = ticksToInch(leftFront.getDeltaPosition());
            double dLB = ticksToInch(leftBack.getDeltaPosition());
            double dRF = ticksToInch(rightFront.getDeltaPosition());
            double dRB = ticksToInch(rightBack.getDeltaPosition());
            dx = ((dLF + dLB + dRF + dRB) / 4.0) * DriveConstants.ForwardMultiplier;
            dy = ((-dLF + dRF + dLB - dRB) / 4.0) * DriveConstants.LateralMultiplier;
            dheading =
                    ((-dLF + dRF - dLB + dRB) / (2.0 * (DriveConstants.TrackWidth + DriveConstants.WheelBase))) * DriveConstants.HeadingMultiplier;
        } else {
            double dLeft = ticksToInch(left.getDeltaPosition());
            double dRight = ticksToInch(right.getDeltaPosition());
            dx = ((dLeft + dRight) / 2.0) * DriveConstants.ForwardMultiplier;
            dy = 0.0; // can't be strafe
            dheading =
                    ((dRight - dLeft) / DriveConstants.TrackWidth) * DriveConstants.HeadingMultiplier;
        }

        double heading = pose.getHeading(); double cosH = Math.cos(heading);
        double sinH = Math.sin(heading); double globalX = dx * cosH - dy * sinH;
        double globalY = dx * sinH + dy * cosH;
        pose = new Pose2d(pose.getX() + globalX, pose.getY() + globalY,
                new Rotation2d(heading + dheading));

        velocity = new Pose2d(globalX / dt, globalY / dt, new Rotation2d(dheading / dt));
    }

    @Override
    public double getMagnitude() {
        return Quaternion.identityQuaternion().magnitude();
    }

    @Override
    public void setPose(Pose2d pose) { this.pose = pose; }
    @Override
    public Pose2d getPose() { return pose; }
    @Override
    public Pose2d getVelocity() { return velocity; }
    @Override
    public double getForwardMultiplier() { return DriveConstants.ForwardMultiplier; }
    @Override
    public double getLateralMultiplier() { return DriveConstants.LateralMultiplier; }
    @Override
    public double getTurnMultiplier() { return DriveConstants.HeadingMultiplier; }
}

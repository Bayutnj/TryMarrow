package org.firstinspires.ftc.teamcode.Localizer;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;

public class PinpointLocalizer implements Localizer {
    private final GoBildaPinpointDriver odo;

    public PinpointLocalizer(HardwareMap map) {
        odo = map.get(GoBildaPinpointDriver.class, DriveConstants.pinpointName);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        odo.resetPosAndIMU();
    }


    @Override
    public void update() {
        odo.update();
    }

    @Override
    public Pose2d getPose() {
        Pose2D p = odo.getPosition();
        return new Pose2d(p.getX(DistanceUnit.INCH), p.getY(DistanceUnit.INCH),
                p.getHeading(AngleUnit.RADIANS));
    }

    @Override
    public void setPose(Pose2d pose) {
        odo.setPosition(new Pose2D(DistanceUnit.INCH,
                pose.getX(), pose.getY(),
                AngleUnit.RADIANS, pose.getHeading()));
    }
    @Override
    public double getMagnitude() { return odo.getQuaternion().magnitude(); }
    @Override
    public Pose2d getVelocity() {
        double vX = odo.getVelX(DistanceUnit.INCH);
        double vY = odo.getVelY(DistanceUnit.INCH);
        double vH = odo.getHeadingVelocity(UnnormalizedAngleUnit.RADIANS);
        return new Pose2d(vX, vY, new Rotation2d(vH));
    }
    @Override
    public double getForwardMultiplier() { return DriveConstants.ForwardMultiplier; }
    @Override
    public double getLateralMultiplier() { return DriveConstants.LateralMultiplier; }
    @Override
    public double getTurnMultiplier() { return DriveConstants.HeadingMultiplier; }
    @Override
    public void resetIMU() { odo.recalibrateIMU(); }
}

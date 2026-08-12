package org.firstinspires.ftc.teamcode.Localizer.Interface;


import com.seattlesolvers.solverslib.geometry.Pose2d;

public interface Localizer {
    void update();
    Pose2d getPose();
    void setPose(Pose2d pose);
    Pose2d getVelocity();
    double getForwardMultiplier();

    double getLateralMultiplier();

    double getTurnMultiplier();
    default void resetIMU() {}
}

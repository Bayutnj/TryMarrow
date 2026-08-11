package org.firstinspires.ftc.teamcode.Localizer.Interface;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Vector2d;


public interface Localizer {
    Pose2d getPose();
    Pose2d getVelocity();
    Vector2d getVelocityVector();
    void setStartPose(Pose2d Start);
    void setPose(Pose2d setPose);
    void update();
    void resetIMU() throws InterruptedException;
    double getIMUHeading();
}

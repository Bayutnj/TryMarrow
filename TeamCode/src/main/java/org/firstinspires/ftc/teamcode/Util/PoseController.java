package org.firstinspires.ftc.teamcode.Util;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.geometry.Translation2d;

import org.firstinspires.ftc.teamcode.Localizer.Main.PoseTracker;
import org.firstinspires.ftc.teamcode.Main.Side.Robot;

public class PoseController {


    public static double getGoalDis(Robot r) {
        return r.CoordinateTracker.getPose().getTranslation().getDistance(
                new Translation2d(r.a.targetPose.getX(), r.a.targetPose.getY()));
    }

    public static Pose2d getFuturePose(PoseTracker p, double kK) {
        return new Pose2d(p.getPose().getX() + p.getMagnitude() * kK,
                p.getPose().getY() + p.getMagnitude() * kK,
                new Rotation2d(p.getPose().getHeading()));
    }
}

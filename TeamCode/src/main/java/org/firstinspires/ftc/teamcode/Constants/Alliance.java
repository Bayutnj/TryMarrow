package org.firstinspires.ftc.teamcode.Constants;

import com.seattlesolvers.solverslib.geometry.Pose2d;

public enum Alliance {
    BLUE(20, new Pose2d(1, 1,1 ), new Pose2d(1, 1, 1)),
    RED(24, new Pose2d(1,1,1 ), new Pose2d(1,1,1 ));
    public final int id;
    public final Pose2d targetPose;
    public final Pose2d spawnPose;
    public Alliance(int id, Pose2d targetPose, Pose2d spawnPose) {
        this.id = id;
        this.targetPose = targetPose;
        this.spawnPose = spawnPose;
    }


}

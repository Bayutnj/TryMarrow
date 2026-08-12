package org.firstinspires.ftc.teamcode.Localizer.Main;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;

public class PoseTracker {
    private final Localizer localizer;
    private Pose2d currentPose;
    private Pose2d currentVelocity;
    private Pose2d startOffset = new Pose2d(0, 0,
            new Rotation2d(0));

    public PoseTracker(Localizer localizer) {
        this.localizer = localizer;
        this.currentPose = localizer.getPose();
        this.currentVelocity = localizer.getVelocity();
    }

    public void update() {
        localizer.update();
        currentPose = localizer.getPose();
        currentVelocity = localizer.getVelocity();
    }

    public Pose2d getPose() {
        return new Pose2d(currentPose.getX() + startOffset.getX(),
                currentPose.getY() + startOffset.getY(),
                new Rotation2d(currentPose.getHeading() + startOffset.getHeading()));
    }

    public Pose2d getVelocity() {
        return currentVelocity;
    }

    public void setPose(Pose2d pose) {
        localizer.setPose(pose);
        currentPose = pose;
    }

    public void setOffset(Pose2d offset) {
        this.startOffset = offset;
    }

    public Localizer getLocalizer() {
        return localizer;
    }
}

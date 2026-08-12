package org.firstinspires.ftc.teamcode.Localizer.Main;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;

public class PoseTracker {
    private final Localizer localizer;
    private Pose2d startingPose = new Pose2d(0, 0, new Rotation2d(0));
    private Pose2d currentVelocity = new Pose2d();
    private Vector2d previousVelocity = new Vector2d();
    private double xOffset= 0;
    private double yOffset = 0;
    private double headingOffset = 0;

    public PoseTracker(Localizer localizer){
        this.localizer = localizer;
        try {
            localizer.resetIMU();;
        } catch (InterruptedException ignored) {
            System.out.println("PoseTracker: resetIMU() interrupted");
        }
    }

    public void update() {
        previousVelocity = getVelocity();

    }


    public Pose2d getVelocity() {
        if (currentVelocity == null) currentVelocity = localizer.getVelocity();
        return currentVelocity;
    }
}

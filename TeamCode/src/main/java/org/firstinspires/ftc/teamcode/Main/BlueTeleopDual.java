package org.firstinspires.ftc.teamcode.Main;

import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Constants.Alliance;
import org.firstinspires.ftc.teamcode.Main.Side.MorretiOpMode;
import org.firstinspires.ftc.teamcode.Util.PoseController;

public class BlueTeleopDual extends MorretiOpMode {

    @Override
    public void onInit() {
        super.onInit();
    }

    @Override
    public void setAlliance() {
        a = Alliance.BLUE;
    }

    @Override
    public void onStart() {
//        super.onStart();

        r.drive.setVoltageCompensation(true);
        r.drive.setField(true);
    }

    @Override
    public void onUpdate() {
//        super.onUpdate();
        Pose2d futurePose = PoseController.getFuturePose(r.CoordinateTracker, 0.6);


        r.drive.drive(base); // move the drivebase

    }
}

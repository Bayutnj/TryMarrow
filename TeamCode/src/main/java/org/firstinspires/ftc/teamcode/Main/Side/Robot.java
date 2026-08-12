package org.firstinspires.ftc.teamcode.Main.Side;

import android.graphics.CornerPathEffect;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.RobotState;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.skeletonarmy.marrow.OpModeManager;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;
import org.firstinspires.ftc.teamcode.Localizer.Main.PoseTracker;
import org.firstinspires.ftc.teamcode.Util.VoltageController;
import org.firstinspires.ftc.teamcode.subsytem.SingleIntake;

public class Robot {
    public static Robot INSTANCE = new Robot();
    private Robot() {
        Voltage = new VoltageController();
        Intake = new SingleIntake();
    }

    public static Robot getInstance() {return INSTANCE;}

    private RobotState robotState = OpModeManager.getRobotState();
    public final SingleIntake Intake;
    public final VoltageController Voltage;
    public PoseTracker CoordinateTracker;
    public  Localizer localizer;

    private final Pose2d defaultPose = new Pose2d(0, 0, new Rotation2d(0));

    public void init(HardwareMap map) {
        init(map, defaultPose);
    }
    public void init(HardwareMap map, Pose2d start) {
        init(map, Constants.createLocalizer(map), start);
    }
    public void init(HardwareMap map, Localizer localizer, Pose2d start) {
        this.localizer = localizer;
        CoordinateTracker = new PoseTracker(localizer);
        CoordinateTracker.setPose(start);

        Voltage.init(map);
        Intake.init(map);
    }

    public void periodic() {
        CoordinateTracker.update();
        Intake.periodic();
    }

    public RobotState getRobotState() {
        return robotState;
    }

}

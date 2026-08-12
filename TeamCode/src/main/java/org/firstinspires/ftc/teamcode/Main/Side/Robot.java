package org.firstinspires.ftc.teamcode.Main.Side;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.RobotState;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.skeletonarmy.marrow.OpModeManager;

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

    private Pose2d defaultePose = new Pose2d(0, 0, new Rotation2d(0));

    public void init(HardwareMap map, Localizer localizer) {
        init(map, localizer, defaultePose);
    }
    public void init(HardwareMap map, Localizer localizer, Pose2d start) {
        this.localizer = localizer;
        CoordinateTracker = new PoseTracker(localizer);
        localizer.setPose(start);

        Intake.init(map);
    }

    public void periodic() {
        Intake.periodic();

    }

    public RobotState getRobotState() {
        return robotState;
    }

}

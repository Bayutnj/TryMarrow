package org.firstinspires.ftc.teamcode.Main.Side;

import com.qualcomm.robotcore.robot.RobotState;
import com.skeletonarmy.marrow.OpModeManager;

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

    public void init() {
    }

    public RobotState getRobotState() {
        return robotState;
    }

}

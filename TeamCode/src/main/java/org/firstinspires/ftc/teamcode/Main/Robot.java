package org.firstinspires.ftc.teamcode.Main;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.Util.VoltageController;
import org.firstinspires.ftc.teamcode.subsytem.SingleIntake;

public class Robot {
    public static Robot INSTANCE = new Robot();
    private Robot() {
        Voltage = new VoltageController();
        Intake = new SingleIntake();
    }

    public static Robot getInstance() {return INSTANCE;}

    public final SingleIntake Intake;
    public final VoltageController Voltage;

    public void init() {
    }
}

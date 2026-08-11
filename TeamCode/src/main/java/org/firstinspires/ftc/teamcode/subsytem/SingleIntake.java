package org.firstinspires.ftc.teamcode.subsytem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;

public class SingleIntake extends SubsystemBase {
    private MotorEx i;
    public void init(HardwareMap hardwareMap){
        i = new MotorEx(hardwareMap, IntakeConstants.Intake, IntakeConstants.motorType);
        i.setInverted(false); // true if need
    }

    public void set(double p) {
        i.set(p);
    }

    public void spinIn() {
        set(IntakeConstants.pin);
    }
    public void spinOut() {
        set(IntakeConstants.pout);
    }
    public void setOff() {
        set(IntakeConstants.off);
    }
}

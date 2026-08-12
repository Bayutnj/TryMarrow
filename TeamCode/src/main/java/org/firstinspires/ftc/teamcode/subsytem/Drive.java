package org.firstinspires.ftc.teamcode.subsytem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.Constants.Enums.DriveType;
import org.firstinspires.ftc.teamcode.Main.Side.Robot;

public class Drive extends SubsystemBase {
    private final DriveType driveType = DriveConstants.driveType;
    private boolean volComps = false;

    public void init(HardwareMap map) {

    }

    public void setVoltageCompensation(boolean e) {
        volComps= e;
    }
    private double volCom(double p) {
        if (volComps) {
            return Robot.getInstance().Voltage.getPower(p);
        }
        return p;
    }
}
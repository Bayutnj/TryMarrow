package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VoltageController {
    private VoltageSensor voltageSensor;
    private ElapsedTime timer;
    double lastVoltage = 13.0;
    public final double NOMINAL_VOLTAGE = 13.0;
    public void init(HardwareMap hardwareMap) {
        timer = new ElapsedTime();
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        lastVoltage = voltageSensor.getVoltage();
    }

    public double getVoltage() {
        if (timer.milliseconds() > 250) {
            lastVoltage = voltageSensor.getVoltage();
            timer.reset();
        }
        return lastVoltage;
    }

    public double getPower(double power, double Nvolt) {
        return power * getVoltage() / Nvolt;
    }
    public double getPower(double power) {
        return getPower(power, NOMINAL_VOLTAGE);
    }
}

package org.firstinspires.ftc.teamcode.subsytem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.InterpLUT;
import com.seattlesolvers.solverslib.util.MathUtils;

import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Main.Side.Robot;

@Disabled
public class Shooter extends SubsystemBase {
    private  MotorEx fl1; // LeftFlw
    private MotorEx fl2; // RightFlw
    private InterpLUT flut = new InterpLUT();
    private SimpleMotorFeedforward ff;
    private PIDController pid;
    private double t = 0; // target Velocity
    private double baseP = .0005;
    private double factorP = .0005; // Factor to divide with kP
    private boolean activated = true;
    private boolean volComp = false;

    public void init(HardwareMap hardwareMap) {
        fl1 = new MotorEx(hardwareMap, ShooterConstants.leftShooter,
                ShooterConstants.motorType);
        fl2 = new MotorEx(hardwareMap, ShooterConstants.rightShooter,
                ShooterConstants.motorType);

        fl1.setInverted(false);
        fl2.setInverted(true);
        fl1.setRunMode(Motor.RunMode.RawPower);
        fl2.setRunMode(Motor.RunMode.RawPower);

        ff = new SimpleMotorFeedforward(.2, .4);
        pid = new PIDController(baseP, .43, .3);

        pid.setTolerance(50, 50);

        flut.add(24, 1000);
        flut.createLUT();

    }

    public double getTarget() {
        return t;
    }

    public double getVelocity() {
        return (fl1.getVelocity() + fl2.getVelocity()) / 2.0;
    }

//    private double getVoltPower(double p) {
//        if (volComp) {
//            return Robot.getInstance().Voltage.getPower(p);
//        }
//        return p;
//    }
//    public void set(double p) {
//       double compensP = getVoltPower(p);
//       fl1.set(compensP);
//       fl2.set(compensP);
//    }

    public void set(double p) {
        if (volComp) {
            p = Robot.getInstance().Voltage.getPower(p);
        }
        fl1.set(p);
        fl2.set(p);
    }

    public void turnOff() {
        activated = false;
        fl1.set(0);
        fl2.set(0);
    }

    public void turnOn() {
        activated = true;
    }

    public void setVoltageCompensation(boolean e) {
        volComp = e;
    }

    public void setTargetByDistance(double d) {
        setTarget(flut.get(d));
    }

    public void setTarget(double t) {
        this.t = t;
    }

    @Override
    public void periodic() {
        double lv = fl1.getVelocity();
        double rv = fl2.getVelocity(); // get
        double lcontrol = pid.calculate(lv, t); // velocity --> power by pid
//        double rcontrol = pid.calculate(rv, t);
        double ffv = ff.calculate(t); // return voltage
        double ffp = ffv / Robot.getInstance().Voltage.getVoltage(); // return power

        boolean e = Math.abs(((lv + rv) / 2.0) - t) > 100.0;
        if (e) {
            double fkP = baseP / factorP;
            pid.setP(fkP);
        } else {
            pid.setP(baseP);
        }

       double fpower = MathUtils.clamp(ffp + lcontrol , -1, 1);

        if (activated) {
            set(fpower);
        }
    }
}
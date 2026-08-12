package org.firstinspires.ftc.teamcode.Localizer.Main;


import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Encoder {
    public static int FORWARD = 1;
    public static int REVERSE = -1;

    private final MotorEx motor;
    private int direction = FORWARD;
    private double previousPosition = 0;

    public Encoder(MotorEx motor) {
        this.motor = motor;
        reset();
    }

    public Encoder setDirection(int direction) {
        this.direction = direction;
        reset();
        return this;
    }

    public void reset() {
        previousPosition = motor.getCurrentPosition() * direction;
    }

    public double getPosition() {
        return motor.getCurrentPosition() * direction;
    }

    public double getDeltaPosition() {
        double current = getPosition();
        double e = current - previousPosition;
        previousPosition = current;
        return e;
    }

    public double getVelocity() {
        return motor.getVelocity() * direction;
    }
}

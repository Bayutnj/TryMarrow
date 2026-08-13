package org.firstinspires.ftc.teamcode.subsytem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.DifferentialDrive;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.seattlesolvers.solverslib.util.MathUtils;

import org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.Constants.Enums.DriveType;
import org.firstinspires.ftc.teamcode.Main.Side.Robot;
import org.firstinspires.ftc.teamcode.Util.InputScaler;

public class Drive extends SubsystemBase {
    private DriveType driveType = DriveConstants.driveType;
    private Robot r = Robot.getInstance();
    private MecanumDrive mecanumDrive;
    private DifferentialDrive differentialDrive;
    private MotorEx left, right; // Differential
    private MotorEx leftFront, leftBack, rightFront, rightBack; // mecanum
    private boolean field = true;


    private Pose2d robotPose = new Pose2d();
    private ChassisSpeeds robotVelocity = new ChassisSpeeds();

    private boolean volComps = false;

    public void init(HardwareMap map) {
        init(map, driveType);
    }

    public void init(HardwareMap map, DriveType driveType) {
        this.driveType = driveType;
        if (driveType == DriveType.MECANUM) {
            leftFront = new MotorEx(map, DriveConstants.leftFrontName);
            rightFront = new MotorEx(map, DriveConstants.rightFrontName);
            leftBack = new MotorEx(map, DriveConstants.leftBackName);
            rightBack = new MotorEx(map, DriveConstants.rightBackName);
//            TODO: Invert Those motors direction
            leftFront.setInverted(false);
            rightFront.setInverted(true);
            leftBack.setInverted(true);
            rightBack.setInverted(false);
            mecanumDrive = new MecanumDrive(false,
                    leftFront, rightFront, leftBack, rightBack);
            mecanumDrive.setMaxSpeed(1.0);
        } else {
            left = new MotorEx(map, DriveConstants.leftName);
            right = new MotorEx(map, DriveConstants.rightName);
            differentialDrive = new DifferentialDrive(true, left, right);
            differentialDrive.setMaxSpeed(1.0);
        }
    }
    public void setField(boolean f) {
        field = f;
    }

    public void drive(GamepadEx g) {
        double heading = r.CoordinateTracker.getPose().getHeading();
        if (driveType == DriveType.MECANUM) {
            double forward_power = InputScaler.scaleInputHigh(g.getLeftY());
            double lateral_power = -InputScaler.scaleInputHigh(g.getLeftX());
            double heading_power = -InputScaler.scaleInputHigh(g.getRightX());

            if (field) {
                mecanumDrive.driveFieldCentric(lateral_power, forward_power,
                        heading_power, heading);
            } else {
                mecanumDrive.driveRobotCentric(lateral_power, forward_power,
                        heading_power);
            }

        } else {
            double forward_power = InputScaler.scaleInputHigh(g.getLeftY());
            double headingPower = -InputScaler.scaleInputHigh(g.getRightX());

            if (field) {return;}

            differentialDrive.arcadeDrive(forward_power, headingPower);
        }
    }

    @Override
    public void periodic() {
        super.periodic();
        robotPose = new Pose2d(r.CoordinateTracker.getPose().getX(),
                r.CoordinateTracker.getPose().getY(), new Rotation2d(r.CoordinateTracker.getPose()
                .getHeading()));
        robotVelocity = ChassisSpeeds.fromFieldRelativeSpeeds(
                new ChassisSpeeds(
                        r.CoordinateTracker.getVelocity().getX(),
                        r.CoordinateTracker.getVelocity().getY(),
                        r.CoordinateTracker.getVelocity().getHeading()
                ),
                new Rotation2d(r.CoordinateTracker.getPose().getHeading())
        );
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
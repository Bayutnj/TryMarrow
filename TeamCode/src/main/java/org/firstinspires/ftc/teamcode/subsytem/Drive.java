package org.firstinspires.ftc.teamcode.subsytem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.DifferentialDrive;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.seattlesolvers.solverslib.p2p.P2PController;

import org.firstinspires.ftc.teamcode.Constants.DriveConstants;
import org.firstinspires.ftc.teamcode.Constants.Enums.DriveType;
import org.firstinspires.ftc.teamcode.Main.Side.Robot;

public class Drive extends SubsystemBase {
    private final DriveType driveType = DriveConstants.driveType;
    private final P2PController follower;
    private final MecanumDrive mecanumDrive;
    private final DifferentialDrive differentialDrive;

    private Pose2d robotPose = new Pose2d();
    private ChassisSpeeds robotVelocity = new ChassisSpeeds();

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
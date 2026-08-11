package org.firstinspires.ftc.teamcode.Localizer;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Vector2d;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.Constants.LocalizerConstants.PinpointConstants;
import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;
import org.firstinspires.ftc.teamcode.Util.PoseConverter;

public class PinpointLocalizer implements Localizer {
    private final GoBildaPinpointDriver odo;
    private final PinpointConstants constants;
    private Pose2d startPose;
    private Pose2d currentVelocity;
    private Vector2d currentVelocityVector;
    private Pose2d pinpointPose;

    public PinpointLocalizer(HardwareMap map, PinpointConstants constants ) {this(map, constants, new Pose2d());}

    public PinpointLocalizer(HardwareMap map, PinpointConstants constants, Pose2d setStartPose) {
        this.constants = constants;
        odo = map.get(GoBildaPinpointDriver.class, constants.mapName);
        setOffset(PinpointConstants.forwardPodX, PinpointConstants.forwardPodY, PinpointConstants.offsetUnit);

        odo.setEncoderResolution(PinpointConstants.pods);

        odo.setEncoderDirections(PinpointConstants.forwardEncoderDirection,
                PinpointConstants.strafeEncoderDirection);

        setStartPose(setStartPose);
        pinpointPose = startPose;
        currentVelocity = new Pose2d();
    }

    @Override
    public Pose2d getPose() {return pinpointPose;}

    @Override
    public Pose2d getVelocity() {return currentVelocity;}

    @Override
    public Vector2d getVelocityVector() {return currentVelocityVector.normalize();}

    @Override
    public void setStartPose(Pose2d Start) {
        setPose(Start);
        this.startPose = Start;
    }

    @Override
    public void setPose(Pose2d setPose) {
        odo.setPosition(Pose2d.convertToPose2D(setPose, DistanceUnit.INCH, AngleUnit.RADIANS));
        pinpointPose = setPose;
    }

    @Override
    public void update() {
        odo.update();
        Pose2d currentPinpointPose = PoseConverter.SDKToSolvers(odo.getPosition());
        currentVelocity = new Pose2d(odo.getVelX(DistanceUnit.INCH),
                odo.getVelY(DistanceUnit.INCH),
                odo.getHeadingVelocity(UnnormalizedAngleUnit.RADIANS));
        pinpointPose = currentPinpointPose;
    }

    @Override
    public void resetIMU() throws InterruptedException {
        resetPinpoint();
    }

    @Override
    public double getIMUHeading() {
        return Double.NaN;
    }

    private void resetPinpoint() {
        odo.resetPosAndIMU();
    }
    private void setOffset(double xOffset, double yOffset, DistanceUnit unit) {
        odo.setOffsets(xOffset, yOffset, unit);
    }

    public GoBildaPinpointDriver getPinpoint() {
        return odo;
    }
}

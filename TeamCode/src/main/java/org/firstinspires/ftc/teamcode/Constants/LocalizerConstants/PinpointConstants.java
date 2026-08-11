package org.firstinspires.ftc.teamcode.Constants.LocalizerConstants;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.FormatFlagsConversionMismatchException;

public class PinpointConstants {
    public static final String mapName = "odo";
    public static final double forwardPodY = 0.0;
    public static final double forwardPodX = 0.0;
    public static final DistanceUnit offsetUnit = DistanceUnit.INCH;
    public static final GoBildaPinpointDriver.GoBildaOdometryPods pods =
            GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;
    public static final GoBildaPinpointDriver.EncoderDirection
            forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
    public static final GoBildaPinpointDriver.EncoderDirection
            strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;

}

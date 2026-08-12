package org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants.Enums.LocalizerType;
import org.firstinspires.ftc.teamcode.Localizer.DriveEncoderLocalizer;
import org.firstinspires.ftc.teamcode.Localizer.Interface.Localizer;
import org.firstinspires.ftc.teamcode.Localizer.PinpointLocalizer;

public class Constants {
    public static LocalizerType localizerType = LocalizerType.ENCODER;

    public static Localizer createLocalizer(HardwareMap map) {
        switch (localizerType) {
            case ENCODER:
                return new DriveEncoderLocalizer(map);
            case PINPOINT:
            default:
                return new PinpointLocalizer(map);
        }
    }
}

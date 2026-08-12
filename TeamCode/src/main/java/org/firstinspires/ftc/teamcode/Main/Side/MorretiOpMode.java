package org.firstinspires.ftc.teamcode.Main.Side;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import java.util.List;

public class MorretiOpMode extends OpMode {
    protected GamepadEx base, helper;
    protected List<LynxModule> allhubs;
    protected Robot r;
    public void onInit() {}
    public void onStart() {}
    public void onUpdate() {}


    @Override
    public void init() {
        base = new GamepadEx(gamepad1);
        helper = new GamepadEx(gamepad2);
        allhubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allhubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        r = Robot.getInstance();


        onInit();
    }

    @Override
    public void start() {
        r.init(hardwareMap, null);
        onStart();
    }

    @Override
    public void loop() {
        for (LynxModule hub : allhubs) {
            hub.clearBulkCache();
        }

        onUpdate();
    }
}

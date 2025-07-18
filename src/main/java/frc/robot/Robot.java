// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.nio.file.PathMatcher;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.OnboardIMU;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.WriteReadDelayTest.WriteReadDelayTest;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {

  }

  int counter = 0;
  //RealSRXEncoder enc = new RealSRXEncoder(0);
  //CANBusStressTest cbst = new CANBusStressTest();
  double prevLoopStartTime = Timer.getFPGATimestamp();
  OnboardIMU imu = new OnboardIMU(OnboardIMU.MountOrientation.kFlat);
  //PWMSparkMax spark = new PWMSparkMax(5);
  //LoggedCamera cam = new LoggedCamera();
  WriteReadDelayTest wrdt = new WriteReadDelayTest();

  @Override
  public void teleopPeriodic() {
    

  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    wrdt.doTest();
  }

  @Override
  public void teleopInit() {
    counter = 0;
  }

  @Override
  public void robotPeriodic() {
    double loopStartTime = Timer.getFPGATimestamp();
    double loopPeriod = loopStartTime - prevLoopStartTime;


    //System.out.println("Hello world");
    //SmartDashboard.putNumber("TestCounter", counter++);
    //enc.getRawAngle_rad();
    SmartDashboard.putNumber("IMU Yaw (rad)", imu.getYawRadians());
    //cbst.update();

    //spark.set(1.0);

    double loopEndTime = Timer.getFPGATimestamp();

    double userCodeDuration = loopEndTime - loopStartTime;

    SmartDashboard.putNumber("LoopPeriod_ms", loopPeriod*1000);
    SmartDashboard.putNumber("UserCodeTime_ms", userCodeDuration*1000);

    prevLoopStartTime = loopStartTime;

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

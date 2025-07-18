package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RealSRXEncoder {

    DutyCycle m_dutyCycle;

    double freq;

    double pulsetime;

    final double MIN_PULSE_TIME = 1E-6; // Minimum pulse time in seconds
    final double MAX_PULSE_TIME = 4.096E-3; // Maximum pulse time in seconds

    public RealSRXEncoder(int port){

        m_dutyCycle = new DutyCycle(0);
    }

    public double getRawAngle_rad() {
        double anglerad = 0.0;
     
        pulsetime = m_dutyCycle.getHighTimeNanoseconds() / 1E9; //Get the time in seconds
        anglerad = ((pulsetime - MIN_PULSE_TIME) / (MAX_PULSE_TIME - MIN_PULSE_TIME)) * 2 * Math.PI;

        SmartDashboard.putNumber("TestSRXEncoder/pulsetime", pulsetime);
        SmartDashboard.putNumber("TestSRXEncoder/anglerad", anglerad);

        return anglerad;

    }
    
}

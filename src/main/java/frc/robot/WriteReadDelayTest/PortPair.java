package frc.robot.WriteReadDelayTest;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PortPair {
    private final DigitalInput inputPort;
    private final DigitalOutput outputPort;

    public PortPair(int inputPortNumber, int outputPortNumber) {
        this.inputPort = new DigitalInput(inputPortNumber);
        this.outputPort = new DigitalOutput(outputPortNumber);
    }

    public double testDelay(){
        this.outputPort.set(false);
        var startTime_us = Timer.getFPGATimestamp() * 1000000;
        this.outputPort.set(true);
        while (!this.inputPort.get()) {
            // Wait for the input to register the change
        }
        var endTime_us = Timer.getFPGATimestamp()* 1000000;
        this.outputPort.set(false);
        var delay_us =  (endTime_us - startTime_us);
        SmartDashboard.putNumber(getName(), delay_us);
        return delay_us;
    }

    public String getName(){
        return "Delay_Port" + outputPort.getChannel() + "_to_Port" + inputPort.getChannel() + "_us";
    }

    public void destroy(){
        this.inputPort.close();
        this.outputPort.close();
    }

}

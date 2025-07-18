package frc.robot.WriteReadDelayTest;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PortPair {
    private final DigitalInput inputPort;
    private final DigitalOutput outputPort;

    public PortPair(int inputPortNumber, int outputPortNumber) {
        this.inputPort = new DigitalInput(inputPortNumber);
        this.outputPort = new DigitalOutput(outputPortNumber);
        this.outputPort.set(false);
    }

    public double testDelay(){

        //Prep - make sure we're stable in the "off" state:
        this.outputPort.set(false);
        while (this.inputPort.get()) {
            // Wait for the input to register being off
        }

        // Timed portion - check time from write to read
        var startTime_ns = System.nanoTime();
        this.outputPort.set(true);
        while (!this.inputPort.get()) {
            // Wait for the input to register the change
        }
        var endTime_ns = System.nanoTime();

        // Reset port value
        this.outputPort.set(false);

        // Calculate and report results
        var delay_ns = endTime_ns - startTime_ns;
        var delay_us =  (delay_ns) / 1000.0;
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

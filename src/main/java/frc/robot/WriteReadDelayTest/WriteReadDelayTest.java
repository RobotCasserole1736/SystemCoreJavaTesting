package frc.robot.WriteReadDelayTest;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.math.Pair;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WriteReadDelayTest {

    // Array of pairs of all port indicies
    private static List<PortPair> PORT_PAIRS = new LinkedList<PortPair>();

    private static final int TEST_ITERATIONS = 1;

    public WriteReadDelayTest(){
        PORT_PAIRS.add(new PortPair(0,1));
        PORT_PAIRS.add(new PortPair(2,3));
        PORT_PAIRS.add(new PortPair(4,5));
    }

    public void doTest(){
        SmartDashboard.putNumber("Delay_NumIterations", TEST_ITERATIONS);
        for (var pair : PORT_PAIRS) {
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                double delay = pair.testDelay();
            }
        }
    }
    
}

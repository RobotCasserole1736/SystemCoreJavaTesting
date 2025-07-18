package frc.robot.WriteReadDelayTest;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.math.Pair;

public class WriteReadDelayTest {

    // Array of pairs of all port indicies
    private static List<Pair<Integer, Integer>> PORT_PAIRS = new LinkedList<Pair<Integer, Integer>>();

    private static final int TEST_ITERATIONS = 1;

    public WriteReadDelayTest(){
        PORT_PAIRS.add(new Pair<Integer, Integer>(0, 1));
        PORT_PAIRS.add(new Pair<Integer, Integer>(1, 0));
        PORT_PAIRS.add(new Pair<Integer, Integer>(2, 3));
        PORT_PAIRS.add(new Pair<Integer, Integer>(3, 2));
        PORT_PAIRS.add(new Pair<Integer, Integer>(4, 5));
        PORT_PAIRS.add(new Pair<Integer, Integer>(5, 4));
    }

    public void doTest(){
        for (var pair : PORT_PAIRS) {
            var pp = new PortPair(pair.getFirst(), pair.getSecond());
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                double delay = pp.testDelay();
            }
            pp.destroy();
        }
    }
    
}

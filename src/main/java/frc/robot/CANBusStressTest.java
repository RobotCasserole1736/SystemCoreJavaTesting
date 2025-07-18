package frc.robot;

import edu.wpi.first.hal.can.CANReceiveMessage;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CANBusStressTest {

    CAN can0;
    CAN can1;
    CAN can2;
    CAN can3;
    CAN can4;

    private static final int MESSAGES_PER_LOOP = 5;

    // byte array of 8 bytesfor test send data
    private static final byte[] TEST_DATA = {0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49};

    public CANBusStressTest(){
        can0 = new CAN(0,0);
        can1 = new CAN(1,1);
        //can2 = new CAN(2,0);
        //can3 = new CAN(3,0);
        //can4 = new CAN(4,0);
    }

    public void update(){
        for (int i = 0; i < MESSAGES_PER_LOOP; i++) {
            can0.writePacket(0x1, TEST_DATA, TEST_DATA.length, 0x00);
        }

        CANReceiveMessage tmp = new CANReceiveMessage();
        for (int i = 0; i < MESSAGES_PER_LOOP; i++) {
            can1.readPacketNew(0x1, tmp);
            SmartDashboard.putString("RxCanPacket", tmp.toString());
            //can2.readPacketNew(0x1, tmp);
            //can3.readPacketNew(0x1, tmp);
            //can4.readPacketNew(0x1, tmp);
        }



    }
    
}

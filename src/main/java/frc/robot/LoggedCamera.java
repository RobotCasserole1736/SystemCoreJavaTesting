package frc.robot;

import javax.xml.crypto.Data;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.datalog.DataLog;
import edu.wpi.first.datalog.RawLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;


public class LoggedCamera {

    Thread m_visionThread;

    public LoggedCamera() {
        DataLogManager.start("logs", "TestBigLog", 0.02);
        DataLog log = DataLogManager.getLog();



        m_visionThread = new Thread(
                () -> {

                    // Start capturing images
                    UsbCamera camera = CameraServer.startAutomaticCapture(0);
                    // Set the resolution
                    camera.setResolution(640, 480);

                    // Get a CvSink. This will capture Mats from the camera
                    CvSink cvSink = CameraServer.getVideo();
                    // Setup a CvSource. This will send images back to the Dashboard
                    CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);

                    // Mats are very memory expensive. Lets reuse this Mat.
                    Mat mat = new Mat();

                    
                    RawLogEntry myRawLog;
                    myRawLog = new RawLogEntry(log, "/cams/" + camera.getName());



                    // This cannot be 'true'. The program will never exit if it is. This
                    // lets the robot stop this thread when restarting robot code or
                    // deploying.
                    while (!Thread.interrupted()) {
                        // Tell the CvSink to grab a frame from the camera and put it
                        // in the source mat. If there is an error notify the output.
                        if (cvSink.grabFrame(mat) == 0) {
                            // Send the output the error.
                            outputStream.notifyError(cvSink.getError());
                            // skip the rest of the current iteration
                            continue;
                        }
                        // Put a rectangle on the image
                        Imgproc.rectangle(
                                mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                        // Give the output stream a new image to display
                        outputStream.putFrame(mat);

                        byte[] imageBytes = new byte[(int) mat.total() * mat.channels()];
                        mat.get(0, 0, imageBytes);
                        //log it
                        myRawLog.append(imageBytes);

                        log.flush();

                        //Delay for 100ms
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // If the thread is interrupted, exit the loop
                            break;
                        }
                    }
                });
        m_visionThread.setDaemon(true);
        m_visionThread.start();
    }

}

package org.usfirst.frc.team4645.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4645.robot.commands.testValuesVision;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */


public class Vision extends Subsystem {

	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	
	private VisionThread visionThread;
	private double centerX1 = 1;
	private double centerY1 = 1;
	private final Object imgLock = new Object();
	
	 

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() 
    {
    	
    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    //draws a rectangle around the biggest contour and gets x and y coordinate of center 
	    visionThread = new VisionThread(camera, new Pipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect rBig = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            
	            
	            synchronized (imgLock) {
	               centerX1 = rBig.x + rBig.width/2;
	               centerY1 = rBig.y + rBig.height/2;
	              
	            }
	        }
	    });
	    visionThread.start();
	    setDefaultCommand(new testValuesVision() );
    }
   
    public double[] returnCoordinate()
	{
    	double[] coordinate={0,0};
    	 synchronized (imgLock) 
    	 {
    		 coordinate[1]= centerX1;
    		 coordinate[0]= centerY1;
    	 }
    	 
		return coordinate;
	}

    
}


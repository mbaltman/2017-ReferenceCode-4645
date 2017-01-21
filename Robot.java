		package org.usfirst.frc.team4645.robot;
		

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.*;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

		/**
		 * The VM is configured to automatically run this class, and to call the
		 * functions corresponding to each mode, as described in the IterativeRobot
		 * documentation. If you change the name of this class or the package after
		 * creating this project, you must also update the manifest file in the resource
		 * directory.
		 */
		public class Robot extends IterativeRobot 
		{
			/*final String defaultAuto = "Default";
			final String customAuto = "My Auto";
			String autoSelected;
			SendableChooser<String> chooser = new SendableChooser<>();
		*/
			
			//variables for drive train
			RobotDrive rb;
			Joystick joy1 ; 
			CANTalon frontLeft, frontRight, backLeft, backRight;
			    
			private static final int IMG_WIDTH = 320;
			private static final int IMG_HEIGHT = 240;
			
			
			public double idealX=60;
			public double idealY = -30;
			
			private VisionThread visionThread;
			private double centerX1 = 1;
			private double centerY1 = 1;
			private double centerX2 = 1;
			private double centerY2 = 1;
			private int flagVariable = 0;
			
			private final Object imgLock = new Object();
			
			/**
			 * This function is run when the robot is first started up and should be
			 * used for any initialization code.
			 */
			@Override
			public void robotInit()
			{
				/*
				chooser.addDefault("Default Auto", defaultAuto);
				chooser.addObject("My Auto", customAuto);
				SmartDashboard.putData("Auto choices", chooser);
				
			*/
				joy1 = new Joystick(1);
				
				
				frontLeft = new CANTalon(4);
				frontRight = new CANTalon(3);
				backLeft = new CANTalon(2);
				backRight = new CANTalon(1);
				
				rb = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
				
				
				 UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
				    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
				    
				    visionThread = new VisionThread(camera, new Pipeline(), pipeline -> {
				        if (!pipeline.filterContoursOutput().isEmpty()) {
				            Rect rBig = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				            //Rect rSmall = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
				            
				            
				            synchronized (imgLock) {
				               centerX1 = rBig.x ;
				               centerY1 = rBig.y ;
				               /*
				               centerX2 = rSmall.x;
				               centerY2 = rSmall.y;
				               */
				               flagVariable= 1;
				            }
				        }
				    });
				    visionThread.start();
			
			} 
			
		
			/**
			 * This autonomous (along with the chooser code above) shows how to select
			 * between different autonomous modes using the dashboard. The sendable
			 * chooser code works with the Java SmartDashboard. If you prefer the
			 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
			 * getString line to get the auto name from the text box below the Gyro
			 *
			 * You can add additional auto modes by adding additional comparisons to the
			 * switch structure below with additional strings. If using the
			 * SendableChooser make sure to add them to the chooser code above as well.
			 */
			@Override
			public void autonomousInit() {
				//autoSelected = chooser.getSelected();
				// autoSelected = SmartDashboard.getString("Auto Selector",
				// defaultAuto);
				//System.out.println("Auto selected: " + autoSelected);
			}
		
			/**
			 * This function is called periodically during autonomous
			 */
			@Override
			public void autonomousPeriodic()
			{
				
				
				double x1;
				double y1;
				double x2;
				double y2;
				synchronized (imgLock) {
					x1 = this.centerX1;
					y1 = this.centerY1;
					x2 = this.centerX2;
					y2 = this.centerY2;
				}
				
				
				if(y1>50)
				{
					
					SmartDashboard.putString("move", "forwards");
				}
				
				if(y1<50)
				{
					
					SmartDashboard.putString("move", "backwards");
				}
				SmartDashboard.putNumber("CenterX1 of tape",x1);
				SmartDashboard.putNumber("CenterY1 of tape",y1);
				SmartDashboard.putNumber("ContoursRan",flagVariable);
			/*
			 * 
			 * comparing contours by height,  verify that they are the correct contours
			 * 
			 * 
			 * 
				if(centerX>180)//if center is too far right move left
				{
					rb.mecanumDrive_Cartesian( -.25, 0, 0, 0.00);
				}
				if(centerX<180)//if center is too far left move right
				{
					rb.mecanumDrive_Cartesian( 0.25, 0, 0, 0.00);
				}
				if(centerY>120)//if y is too far up on the screen, too close => move backwards
				{
					rb.mecanumDrive_Cartesian(0 , .25, 0, 0.00);
				}
				if(centerY<120)//if y is too far down on the screen, too far => move forwards
				{
					rb.mecanumDrive_Cartesian(0 , -.25, 0, 0.00);
				}
				
				*/
				
				
				
				
				
			}
				
			
		
			/**
			 * This function is called periodically during operator control
			 */
			@Override
			public void teleopPeriodic() {
				
				double x1;
				double y1;
				double x2;
				double y2;
				synchronized (imgLock) {
					x1 = this.centerX1;
					y1 = this.centerY1;
					x2 = this.centerX2;
					y2 = this.centerY2;
				}
				if(y1>90 && y1<95)
				{
					rb.mecanumDrive_Cartesian(0, 0, 0,0);
					SmartDashboard.putString("move", "stop");
				}
				
				else if(y1>90)
				{
					rb.mecanumDrive_Cartesian(0, 0, .1,0);
					SmartDashboard.putString("move", "forwards");
				}
				
				else if(y1<95)
				{
					rb.mecanumDrive_Cartesian(0, 0, -.1,0);
					SmartDashboard.putString("move", "backwards");
				}
				
				
				SmartDashboard.putNumber("CenterX1 of tape",x1);
				SmartDashboard.putNumber("CenterY1 of tape",y1);
				SmartDashboard.putNumber("ContoursRan",flagVariable);
				
				//SmartDashboard.putNumber("CenterX2 of tape",centerX2);
				//SmartDashboard.putNumber("CenterY2 of tape",centerY2);
			
				
				//rb.mecanumDrive_Cartesian(joy1.getX(), joy1.getY(), joy1.getTwist(),0);
				
			}
		
			/**
			 * This function is called periodically during test mode
			 */
			@Override
			public void testPeriodic() {
				rb.mecanumDrive_Cartesian(joy1.getX(), joy1.getY(), joy1.getTwist(),0);
				/*double tempYMag = 0;
				double tempXMag = 0;
				double tempTwist= 0;
				rb.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
				rb.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		       
		        	double turnSpeed = ((joy1.getRawAxis(3) + 2)) ; //
		    		double YSpeed = (-1* (joy1.getRawAxis(3) + 2)) ;    //  general speed controlled by 4th axis
		    		double XSpeed = (-1* (joy1.getRawAxis(3) + 2)) ;    // (why * 1.5?) ---> ***NEEDS TESTING
		        	tempYMag = joy1.getY(); 
					tempXMag = joy1.getX();
					tempTwist = joy1.getTwist();
					if(joy1.getRawButton(2)){tempXMag  = 0;}//if trigger, no strafe
		    		if(joy1.getRawButton(3)){tempTwist = 0;}//if thumb button, no turn
		    		if(joy1.getRawButton(1)){tempYMag  = 0;}//if button 3, no forward
		        	rb.mecanumDrive_Cartesian( tempXMag/XSpeed, tempYMag/YSpeed, tempTwist/turnSpeed, 0.00);
*/
			}
		}
		

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
			  
			//variables for vision
			private static final int IMG_WIDTH = 320;
			private static final int IMG_HEIGHT = 240;
			
			
			private VisionThread visionThread;
			private double centerX1 = 1;
			private double centerY1 = 1;
			private boolean centeredX=false;
			private boolean centeredY=false;
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
				    //draws a rectangle around the biggest contour and gets x and y coordinate of center 
				    visionThread = new VisionThread(camera, new Pipeline(), pipeline -> {
				        if (!pipeline.filterContoursOutput().isEmpty()) {
				            Rect rBig = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				            //Rect rSmall = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
				            
				            
				            synchronized (imgLock) {
				               centerX1 = rBig.x + rBig.width/2;
				               centerY1 = rBig.y + rBig.height/2;
				              
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
				
				//returns center x and center y and expected motion 
				centerOnTest(160,90);
				
			}
				
			
		
			/**
			 * This function is called periodically during operator control
			 */
			@Override
			public void teleopPeriodic() 
			{
				while( !returnCentered(160,90))
				{centerOn(160, 90);}
					
					
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
			
			public boolean returnCentured()
			{
			return true;
			}
			public void centerOnTest(double iX, double iY)
			
			{
				
				double x1;
				double y1;
				double idealX=iX;
				double idealY=iY;
				
				synchronized (imgLock) 
				{
					x1 = this.centerX1;
					y1 = this.centerY1;
					
				}
				
				
		
				if(!centeredX && !centeredY)
				{
				    if(y1>idealY && y1<idealY+5)
					{
				    	SmartDashboard.putString("move", "stop");
				    	centeredY=true;
					}
					
					else if(y1>idealY+5)
					{
						SmartDashboard.putString("move", "forwards");
					}
					
					else if(y1<idealY)
					{
						SmartDashboard.putString("move", "backwards");
					}
				}
				else if(!centeredX)
				{
				
				//right side reversed
					 if(x1<idealX && x1>(idealX-5))
					{
						 SmartDashboard.putString("move", "stop");
					}			
				    else if(x1<idealX-5)
					{
				    	SmartDashboard.putString("move", "left");
					}
			
					else if(x1>idealX)
					{
						SmartDashboard.putString("move", "right");
					}
				}
				
				SmartDashboard.putNumber("CenterX1 of tape",x1);
				SmartDashboard.putNumber("CenterY1 of tape",y1);		
				
			}
			
			
			
			public void centerOn(double iX, double iY)
			{
				
				double x1;
				double y1;
				double ymove=0;
				double idealX=iX;
				double idealY=iY;
				
				synchronized (imgLock) 
				{
					x1 = this.centerX1;
					y1 = this.centerY1;
					
				}
				
				
		
				if(!centeredX && !centeredY)
				{
				    if(y1>idealY && y1<idealY+5)
					{
						stopRobot();
						centeredY=true;
					}
					
					else if(y1>idealY+5)
					{
						ymove=.2;
						SmartDashboard.putString("move", "forwards");
					}
					
					else if(y1<idealY)
					{
						//centeredY=true;
						ymove=-.2;
						SmartDashboard.putString("move", "backwards");
					}
					
				    rb.mecanumDrive_Cartesian(0, 0, ymove,0);
				}
				else if(!centeredX)
				{
				
				//right side reversed
					 if(x1<idealX && x1>(idealX-5))
					{
						 stopRobot();
						 centeredX=true;
					}			
				    else if(x1<idealX-5)
					{
						strafeLeft();
					}
			
					else if(x1>idealX)
					{
						strafeRight();
					}
				}
				
				SmartDashboard.putNumber("CenterX1 of tape",x1);
				SmartDashboard.putNumber("CenterY1 of tape",y1);
				
			}
			
			public void strafeLeft()
			{
				frontLeft.set( -.2 );
				backLeft.set(.2);
				
				frontRight.set(-.2);
				backRight.set(.2);
				SmartDashboard.putString("move", "left");
			}
			public void strafeRight()
			{
				frontLeft.set( .2);
				backLeft.set(-.2);
				
				frontRight.set(.2);
				backRight.set(-.2);
				SmartDashboard.putString("move", "right");
			}
			public void stopRobot()
			{
				frontLeft.set( 0 );
				backLeft.set(0);
				
				frontRight.set(0);
				backRight.set(0);
				SmartDashboard.putString("move", "stop");
			}
		}
		

package org.usfirst.frc.team4645.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {
	Joystick joy1;
	Timer timer;
	CANTalon motorOne, motorTwo, motorThree;
	Servo servy;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		joy1 = new Joystick(0);
		timer = new Timer();
		motorOne = new CANTalon(0);
		motorTwo = new CANTalon(1);
		motorThree = new CANTalon(3);
		servy = new Servo(3);
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {

	}



	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
		
		
		//motorOne.setF(0.1097);
		/*motorOne.setP(0);
		motorOne.setI(0);
		motorOne.setD(0);*/
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		
		
			if(joy1.getTrigger())
			{
				motorOne.set(-.77);
				//motorTwo.set(-1);
			}
			else if (joy1.getRawButton(3))
			{
				motorOne.set(-1);
				//motorTwo.set(1);			
			}
			else if (joy1.getRawButton(4))
			{
				motorOne.set(1);
				//motorTwo.set(-1);
			}
			else if (joy1.getRawButton(5))
			{
				motorOne.set(0.90);
				//motorTwo.set(-.90);
			}
			else if (joy1.getRawButton(6))
			{
				motorOne.set(0.85);
				//motorTwo.set(-.85);
			}
			else if (joy1.getRawButton(7))
			{
				motorThree.set(-1);
				//motorTwo.set(0.6);
			}
			else if (joy1.getRawButton(8))
			{
				motorOne.set(.70);
				//motorTwo.set(0.6);
			}
			else if (joy1.getRawButton(9))
			{
				motorOne.set(.65);
				//motorTwo.set(0.6);
			}
			else if (joy1.getRawButton(10))
			{
				motorOne.set(0.6);
				//motorTwo.set(0.6);
			}
			/*else if (joy1.getRawButton(11))
			{
				motorOne.set(.55);
				//motorTwo.set(0.6);
			}*/
			else if (joy1.getRawButton(12))
			{
				motorOne.set(.5);
				//motorTwo.set(0.6);
			}
			else
			{
				motorOne.set(0);
				//motorTwo.set(0);
				motorThree.set(0);
			}
			
			if (joy1.getRawButton(11)) {
				motorTwo.set(-.475);
				//motorThree.set(1);
				
			}
			else {
				motorTwo.set(0);
				//motorThree.set(0);
			}
			
			double tempXMag = joy1.getX();
			double tempYMag = joy1.getY();
			
			/*double angle = Math.atan2(tempYMag, tempXMag);
			angle *= 180/Math.PI;
			
			if (angle < 0) {}
			else
			{
				angle = 180 - angle;
				double servoValue = angle / 180;
				servy.set(servoValue);
			}
			
			System.out.print(servy.getAngle() + " ");*/
			
			double throttle = joy1.getThrottle();
			//throttle += 2;
			//throttle /= 2;
			servy.set(throttle);
			
			
		
	}

	
}

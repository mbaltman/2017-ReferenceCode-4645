package org.usfirst.frc.team4645.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
	CANTalon steeringMotor;
	CANTalon drivingMotor;
	final double FORWARD_DIR_ERROR = 135;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		joy1 = new Joystick(0);
		timer = new Timer();
		steeringMotor = new CANTalon(0);
		steeringMotor.setProfile(0);
		drivingMotor = new CANTalon(1);
		
		/* first choose the sensor */
        steeringMotor.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
        //steeringMotor.reverseOutput(true);
        //_talon.configEncoderCodesPerRev(80); // if using FeedbackDevice.QuadEncoder
        //steeringMotor.configPotentiometerTurns(1024); // if using FeedbackDevice.AnalogEncoder or AnalogPot
        steeringMotor.configNominalOutputVoltage(+0.0f, -0.0f);
        steeringMotor.configPeakOutputVoltage(+12.0f, -12.0f);
        
        
        steeringMotor.setProfile(0);
        steeringMotor.setP(25);
        steeringMotor.setI(0);
        steeringMotor.setD(250);
        steeringMotor.setAllowableClosedLoopErr(2);
        

		
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
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double tempXMag = joy1.getX();
		double tempYMag = joy1.getY();
		double tempMag = joy1.getMagnitude();
		
		//steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		//steeringMotor.set(-1500);
		double curPosition = steeringMotor.getAnalogInPosition();
		SmartDashboard.putNumber("curPosition", curPosition);
		
		/*double joyPosition = Math.atan2(tempYMag, tempXMag);
		joyPosition *= 180/Math.PI;
		if (joyPosition < 0) {
			joyPosition += 360;
		}
		
		//with *=, it doesn't work for some reason
		joyPosition = joyPosition * 1023 / 360;
		
		SmartDashboard.putNumber("joyPosition", joyPosition);
		
		if (joy1.getMagnitude() > 0.25) {
			steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position);
			steeringMotor.set(joyPosition);
				
			//steeringMotor.set(tempXMag * .1);
				
			drivingMotor.set(tempMag);
				
		}*/
		
		
		//makes the encoder position into an angle
		double normCurPosition = curPosition % 1023;
		SmartDashboard.putNumber("initialNormCurPosition", normCurPosition); //at -1500, should be -477
		
		if (normCurPosition < 0) {
			normCurPosition += 1023;
		}
		SmartDashboard.putNumber("positiveNormCurPosition", normCurPosition); //at -1500, should be 546
		
		double curAngle = (normCurPosition / 1023) * 2 * Math.PI;
		SmartDashboard.putNumber("curAngle", curAngle); //at -1500, should be 3.3535 radians or 192.14 degrees
		
		//finding the components of joyAngle on the encoder angle axis
		double newXMag = ((tempXMag * Math.cos(curAngle)) + (tempYMag * Math.sin(curAngle)));
		double newYMag = ((tempYMag * Math.cos(curAngle)) - (tempXMag * Math.sin(curAngle)));
		SmartDashboard.putNumber("newXMag", newXMag);
		SmartDashboard.putNumber("newYMag", newYMag);
		
		if (joy1.getMagnitude() > 0.25) {
			//setting the wheel position
			double angleDif = Math.atan2(newYMag,newXMag);
			double positionDif = angleDif * (1023/(2 * Math.PI));
			SmartDashboard.putNumber("positionDif", positionDif);
			
			steeringMotor.changeControlMode(CANTalon.TalonControlMode.Position);
			steeringMotor.set(curPosition + positionDif + FORWARD_DIR_ERROR);
			
			SmartDashboard.putNumber("newSteeringPosition", steeringMotor.getAnalogInPosition());
			
			if (joy1.getTrigger()) {
				drivingMotor.set(tempMag);
			}
			else {
				drivingMotor.set(0);
			}
		}
		
		
		
			
	}
		
	
	
}













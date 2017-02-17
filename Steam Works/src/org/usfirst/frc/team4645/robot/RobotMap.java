package org.usfirst.frc.team4645.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Servo;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	
	
	//PWM Ports
	//motors
	public static final int driveFR = 0;
	public static final int hangerMotorB = 1;
	public static final int hangerMotorT = 2;
	public static final int feederMotor = 3;
	public static final int driveBL = 5;
	public static final int intakeMotor = 6;
	//servos
	public static final int reservoirServo = 7;
	public static final int gearServoDrop =8;
	public static final int gearServoPush =9;
	
	
	//SRX IDs
	public static final int steerFR = 0;
	public static final int steerFL = 1;
	public static final int steerBR = 2;
	public static final int steerBL = 3;
	public static final int driveFL = 4;
	public static final int driveBR = 5;
	public static final int shooterMotor = 6;
	
	
	//Steering errors
	public static final double FRONTRIGHT_ERROR = 163;
	public static final double FRONTLEFT_ERROR = 121;
	public static final double BACKRIGHT_ERROR = -5;
	public static final double BACKLEFT_ERROR = 208;
	
	//Radius angles (degrees) and value (meters)
	public static final double FRONTRIGHT_RADANGLE = 32;
	public static final double FRONTLEFT_RADANGLE = 148;
	public static final double BACKRIGHT_RADANGLE = 328;
	public static final double BACKLEFT_RADANGLE = 212;
	
	public static final double RADIUS = 0.425;
	
	//Shooter speeds
	public static final double closerSpeed = -475;
	public static final double fartherSpeed = -600;
	
	
	
}

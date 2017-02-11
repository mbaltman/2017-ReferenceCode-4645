package org.usfirst.frc.team4645.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int steerFR = 0;
	public static final int steerFL = 1;
	public static final int steerBR = 2;
	public static final int steerBL = 3;
	
	public static final int driveFR = 4;
	public static final int driveFL = 5;
	public static final int driveBR = 6;
	public static final int driveBL = 7;
	
	public static final double FRONTRIGHT_ERROR = 163;
	public static final double FRONTLEFT_ERROR = 121;
	public static final double BACKRIGHT_ERROR = -5;
	public static final double BACKLEFT_ERROR = 208;
	
	public static final double FRONTRIGHT_RADANGLE = 32;
	public static final double FRONTLEFT_RADANGLE = 148;
	public static final double BACKRIGHT_RADANGLE = 328;
	public static final double BACKLEFT_RADANGLE = 212;
	
	public static final double RADIUS = 0.425;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

package org.usfirst.frc.team4645.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Servo;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Servo reservoirServo = new Servo(0);
	
	public static CANTalon intakeMotor= new CANTalon(2);
	
	public static CANTalon hangerMotor1 = new CANTalon(1);
	public static CANTalon hangerMotor2 = new CANTalon(3);
	
	public static Servo gearServo1 = new Servo(1);
	public static Servo gearServo2 = new Servo(2);
	
	
}

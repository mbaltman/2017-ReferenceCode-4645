package org.usfirst.frc.team4645.robot.subsystems;

import org.usfirst.frc.team4645.robot.RobotMap;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gears extends Subsystem 
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static final Servo gearDropServo =  new Servo(RobotMap.gearServoDrop);
	public static final Servo gearPushServo =  new Servo(RobotMap.gearServoPush);
	
	
	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void dropGear()
    {
    	gearDropServo.set(0);
    }
   
    public void resetDropGear()
    {
    	gearDropServo.set(0);
    }
   
}


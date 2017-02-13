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
	public static final Servo gearDrop =  new Servo(RobotMap.gearServoDrop);
	public static final Servo gearPush =  new Servo(RobotMap.gearServoPush);
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void dropGear()
    {
    	gearDrop.set(0);
    }
    public void pushGear()
    {
    	gearPush.set(1);
    	
    }
    public void resetDropGear()
    {
    	gearDrop.set(1);
    }
    public void resetPushGear()
    {
    	gearPush.set(0);
    }
}


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
	Servo gear1= RobotMap.gearServo1;
	Servo gear2 = RobotMap.gearServo2;
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void dropGear()
    {
    	gear1.set(0);
    }
    public void pushGear()
    {
    	gear2.set(1);
    	
    }
    public void resetDropGear()
    {
    	gear1.set(0);
    }
    public void resetPushGear()
    {
    	gear2.set(0);
    }
}


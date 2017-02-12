package org.usfirst.frc.team4645.robot.subsystems;

import org.usfirst.frc.team4645.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climbing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static CANTalon climbingLeft = new CANTalon(RobotMap.hangerMotorL);
	public static CANTalon climbingRight = new CANTalon(RobotMap.hangerMotorR);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stopClimb()
    {
    climbingLeft.set(0);
    climbingRight.set(0);
    }
    public void startClimb()
    {
    	climbingLeft.set(1);
        climbingRight.set(-1);
    
    }
}


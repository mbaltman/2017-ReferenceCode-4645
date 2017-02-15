package org.usfirst.frc.team4645.robot.subsystems;

import org.usfirst.frc.team4645.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climbing extends Subsystem 
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static Talon climbingLeftMotor = new Talon(RobotMap.hangerMotorL);
	public static Talon climbingRightMotor = new Talon(RobotMap.hangerMotorR);
	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stopClimb()
    {
     	climbingLeftMotor.set(0);
    	climbingRightMotor.set(0);
    }
    public void startClimb()
    {
    	climbingLeftMotor.set(1);
        climbingRightMotor.set(-1);
    
    }
}


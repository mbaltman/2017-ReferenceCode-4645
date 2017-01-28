package org.usfirst.frc.team4645.robot.subsystems;

import org.usfirst.frc.team4645.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climbing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon climbing1=RobotMap.hangerMotor1;
	CANTalon climbing2=RobotMap.hangerMotor2;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void stopClimb()
    {
    climbing1.set(0);
    climbing2.set(0);
    }
    public void startClimb()
    {
    	climbing1.set(1);
        climbing2.set(-1);
    
    }
}


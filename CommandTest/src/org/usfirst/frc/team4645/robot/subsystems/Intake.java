package org.usfirst.frc.team4645.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4645.robot.RobotMap;

import com.ctre.CANTalon;
/**
 *
 */
public class Intake extends Subsystem {

   
	CANTalon intakeMotorlocal=RobotMap.intakeMotor;

    public void initDefaultCommand() {
 
    }
    
    public void intakeIn(){
    	intakeMotorlocal.set(.5);
    }
    
    
    public void intakeStop()
    {
    	intakeMotorlocal.set(0);
    }
}


package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4645.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4645.robot.subsystems.*;

/**
 *
 */
public class testValuesVision extends Command {

	double focalLength= 510;
	double distanceAway=0;
   
    public testValuesVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.visionSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       
    	double[] coordinateBoiler=Robot.visionSubsystem.returnBoilerInformation();
    	
        SmartDashboard.putNumber("Boiler: y coordinate",coordinateBoiler[3]);
        SmartDashboard.putNumber("Boiler: x coordinate",coordinateBoiler[2]);
        SmartDashboard.putNumber("Boiler: height", coordinateBoiler[4]);
        SmartDashboard.putNumber("Boiler: width", coordinateBoiler[5]);
       
        
        
        SmartDashboard.putNumber("Boiler: distance away",coordinateBoiler[1]);  
        SmartDashboard.putNumber("Boiler: distance off center",coordinateBoiler[0]);  
        
        
        
        double[] coordinateGear=Robot.visionSubsystem.returnGearInformation();
    	
        SmartDashboard.putNumber("Gear: y coordinate",coordinateGear[3]);
        SmartDashboard.putNumber("Gear: x coordinate",coordinateGear[2]);
        SmartDashboard.putNumber("Gear: height", coordinateGear[4]);
        SmartDashboard.putNumber("Gear: width", coordinateGear[5]);
       
        
        
        SmartDashboard.putNumber("Gear: distance away",coordinateGear[1]);  
        SmartDashboard.putNumber("Gear: Boiler: distance off center",coordinateGear[0]);  
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

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
       
    	double[] coordinateHere=Robot.visionSubsystem.returnCoordinate();
    	
        SmartDashboard.putNumber("y coordinate",coordinateHere[1]);
        SmartDashboard.putNumber("x coordinate",coordinateHere[0]);
        SmartDashboard.putNumber("height", coordinateHere[3]);
        SmartDashboard.putNumber("width", coordinateHere[2]);
       
        
        
        SmartDashboard.putNumber("distanceAway",distanceAway);  
        
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

package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;
import org.usfirst.frc.team4645.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReservoirCommand extends Command {

	
	
	
    public ReservoirCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.reservoirSubsystem);
    
    	
    }
    
   

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.reservoirSubsystem.spinOut();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.reservoirSubsystem.servo1.get()<.51)
    	Robot.reservoirSubsystem.spinIn();
    	
       if(Robot.reservoirSubsystem.servo1.get()>.99)
    	{
    	Robot.reservoirSubsystem.spinOut();
    	}
       
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.reservoirSubsystem.spinOut();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.reservoirSubsystem.spinOut();
   
    }
}

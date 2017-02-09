package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4645.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4645.robot.subsystems.*;

/**
 *
 */
public class centerInX extends Command {
	
	boolean centeredX=false;
	double idealX=0;
   
	public centerInX(double x) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.visionSubsystem);
    	//requires(Robot.drivetrainSubsystem);
    	idealX =x;   	
    }
	public centerInX(double x,double time) {
        // for a timer 
    	//requires(Robot.visionSubsystem);
    	//requires(Robot.drivetrainSubsystem);
    	idealX =x;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x1 = Robot.visionSubsystem.returnCoordinate()[0];
    	
    	
    	if(!centeredX)
		{
			//right side reversed
			 if(x1<idealX && x1>(idealX+5))
			{
				 SmartDashboard.putString("move", "stop");
				 centeredX=true;
			}			
		    else if(x1>idealX+5)
			{
		    	SmartDashboard.putString("move", "left");
			}
	
			else if(x1<idealX)
			{
				SmartDashboard.putString("move", "right");
			}
		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return centeredX;
        //put in a timer option so that for autonomous it will just stop after maybe two seconds if it starts going crazy
    }

    // Called once after isFinished returns true
    protected void end() {
    	//stop all wheels, so that robot stops in its ideal position
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//stop all wheels so that robot stops in its ideal position
    }
}

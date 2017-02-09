package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class centerInY extends Command {

	boolean centeredY=false;
	double idealY=0;
   
	public centerInY(double y) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.visionSubsystem);
    	//requires(Robot.drivetrainSubsystem);
    	idealY =y;   	
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double y1 = Robot.visionSubsystem.returnCoordinate()[1];
    	
    	
    	if(!centeredY)
		{
			//right side reversed
			 if(y1<idealY && y1>(idealY+5))
			{
				 SmartDashboard.putString("move", "stop");
				 centeredY=true;
			}			
		    else if(y1>idealY+5)
			{
		    	SmartDashboard.putString("move", "forwards");
			}
	
			else if(y1<idealY)
			{
				SmartDashboard.putString("move", "backwards");
			}
		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return centeredY;
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

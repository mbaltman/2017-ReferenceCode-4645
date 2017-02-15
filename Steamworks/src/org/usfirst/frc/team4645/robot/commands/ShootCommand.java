package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command 
{
	
    public ShootCommand() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterSubsystem);
    }
   

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.shooterSubsystem.setShooterSpeed();
    	if(Robot.shooterSubsystem.shooter.get()==Robot.shooterSpeed)
    	{
    		Robot.shooterSubsystem.pgMotorMove();
    	}
    	else
    	{
    		Robot.shooterSubsystem.pgMotorStop();
    	}
    	//only turns true if timer is created and then runs out
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;//only returns true if timer is created and then runs out
       
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.shooterSubsystem.shooterStop();//stops all motors when done
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.shooterSubsystem.shooterStop();//stops all motors when done
    }
}

package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4645.robot.OI;
import org.usfirst.frc.team4645.robot.subsystems.SwerveDrive;

import com.ctre.CANTalon;

import org.usfirst.frc.team4645.robot.Robot;
import org.usfirst.frc.team4645.robot.RobotMap;

/**
 *
 */
public class DefaultSwerve extends Command
{

    public DefaultSwerve() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.swerveDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	
    	//get gyro position
    	double gyroPosition = (SwerveDrive.gyro.getAngle()) * (1023/360);
    	
    	//get wheel positions
    	double curFRPosition = Robot.swerveDrive.getPosition(SwerveDrive.steeringMotorFrontRight);
    	double curFLPosition = Robot.swerveDrive.getPosition(SwerveDrive.steeringMotorFrontLeft);
    	double curBRPosition = Robot.swerveDrive.getPosition(SwerveDrive.steeringMotorBackRight);
    	double curBLPosition = Robot.swerveDrive.getPosition(SwerveDrive.steeringMotorBackLeft);
    	
    	//get wheel angles
    	double curFRAngle = Robot.swerveDrive.getAngle(curFRPosition);
    	double curFLAngle = Robot.swerveDrive.getAngle(curFLPosition);
    	double curBRAngle = Robot.swerveDrive.getAngle(curBRPosition);
    	double curBLAngle = Robot.swerveDrive.getAngle(curBLPosition);
    	
    	//get rotation angles
    	double rotFRAngle = Robot.swerveDrive.getRotationAngle(OI.tempZMag, RobotMap.FRONTRIGHT_RADANGLE);
    	double rotFLAngle = Robot.swerveDrive.getRotationAngle(OI.tempZMag, RobotMap.FRONTLEFT_RADANGLE);
    	double rotBRAngle = Robot.swerveDrive.getRotationAngle(OI.tempZMag, RobotMap.BACKRIGHT_RADANGLE);
    	double rotBLAngle = Robot.swerveDrive.getRotationAngle(OI.tempZMag, RobotMap.BACKLEFT_RADANGLE);
    	
    	//get rotation components
    	double radXFR = Robot.swerveDrive.getRotCompX(rotFRAngle, OI.tempZMag);
    	double radYFR = Robot.swerveDrive.getRotCompY(rotFRAngle, OI.tempZMag);
    	double radXFL = Robot.swerveDrive.getRotCompX(rotFLAngle, OI.tempZMag);
    	double radYFL = Robot.swerveDrive.getRotCompY(rotFLAngle, OI.tempZMag);
    	double radXBR = Robot.swerveDrive.getRotCompX(rotBRAngle, OI.tempZMag);
    	double radYBR = Robot.swerveDrive.getRotCompY(rotBRAngle, OI.tempZMag);
    	double radXBL = Robot.swerveDrive.getRotCompX(rotBLAngle, OI.tempZMag);
    	double radYBL = Robot.swerveDrive.getRotCompY(rotBLAngle, OI.tempZMag);
		
		//calc total components
    	double totalXFR = Robot.swerveDrive.getTotalComp(radXFR, OI.tempXMag);
    	double totalYFR = Robot.swerveDrive.getTotalComp(radYFR, OI.tempYMag);
    	double totalXFL = Robot.swerveDrive.getTotalComp(radXFL, OI.tempXMag);
    	double totalYFL = Robot.swerveDrive.getTotalComp(radYFL, OI.tempYMag);
    	double totalXBR = Robot.swerveDrive.getTotalComp(radXBR, OI.tempXMag);
    	double totalYBR = Robot.swerveDrive.getTotalComp(radYBR, OI.tempYMag);
    	double totalXBL = Robot.swerveDrive.getTotalComp(radXBL, OI.tempXMag);
    	double totalYBL = Robot.swerveDrive.getTotalComp(radYBL, OI.tempYMag);
		
		
		//calc total mags and max
		double totalFR = Math.sqrt(Math.pow(totalXFR, 2) + Math.pow(totalYFR, 2));
		double totalFL = Math.sqrt(Math.pow(totalXFL, 2) + Math.pow(totalYFL, 2));
		double totalBR = Math.sqrt(Math.pow(totalXBR, 2) + Math.pow(totalYBR, 2));
		double totalBL = Math.sqrt(Math.pow(totalXBL, 2) + Math.pow(totalYBL, 2));
		
		double max = Robot.swerveDrive.calcMax(totalFR, totalFL, totalBR, totalBL);
		
		if (max > 1)
		{
			totalFR /= max;
			totalFL /= max;
			totalBR /= max;
			totalBL /= max;
		}
		
		//calc relative magnitudes
		double newXMagFR = Robot.swerveDrive.calcRelMagX(totalXFR, totalYFR, curFRAngle);
		double newYMagFR = Robot.swerveDrive.calcRelMagY(totalYFR, totalXFR, curFRAngle);
		double newXMagFL = Robot.swerveDrive.calcRelMagX(totalXFL, totalYFL, curFLAngle);
		double newYMagFL = Robot.swerveDrive.calcRelMagY(totalYFL, totalXFL, curFLAngle);
		double newXMagBR = Robot.swerveDrive.calcRelMagX(totalXBR, totalYBR, curBRAngle);
		double newYMagBR = Robot.swerveDrive.calcRelMagY(totalYBR, totalXBR, curBRAngle);
		double newXMagBL = Robot.swerveDrive.calcRelMagX(totalXBL, totalYBL, curBLAngle);
		double newYMagBL = Robot.swerveDrive.calcRelMagY(totalYBL, totalXBL, curBLAngle);
		
		//get position difference
		double positionDifFR = Robot.swerveDrive.getPositionDif(newXMagFR, newYMagFR);
		double positionDifFL = Robot.swerveDrive.getPositionDif(newXMagFL, newYMagFL);
		double positionDifBR = Robot.swerveDrive.getPositionDif(newXMagBR, newYMagBR);
		double positionDifBL = Robot.swerveDrive.getPositionDif(newXMagBL, newYMagBL);
		
		//set motor output
		if (max > 0.15) 
		{
			
			Robot.swerveDrive.setSteeringPosition(SwerveDrive.steeringMotorFrontRight, curFRPosition, positionDifFR, gyroPosition, RobotMap.FRONTRIGHT_ERROR);
			Robot.swerveDrive.setSteeringPosition(SwerveDrive.steeringMotorFrontLeft, curFLPosition, positionDifFL, gyroPosition, RobotMap.FRONTLEFT_ERROR);
			Robot.swerveDrive.setSteeringPosition(SwerveDrive.steeringMotorBackRight, curBRPosition, positionDifBR, gyroPosition, RobotMap.BACKRIGHT_ERROR);
			Robot.swerveDrive.setSteeringPosition(SwerveDrive.steeringMotorBackLeft, curBLPosition, positionDifBL, gyroPosition, RobotMap.BACKLEFT_ERROR);
			
			SwerveDrive.drivingMotorFrontRight.set(totalFR);
			SwerveDrive.drivingMotorFrontLeft.set(totalFL);
			SwerveDrive.drivingMotorBackRight.set(totalBR);
			SwerveDrive.drivingMotorBackLeft.set(totalBL);
			
		}
		else 
		{
			SwerveDrive.drivingMotorFrontRight.set(0);
			SwerveDrive.drivingMotorFrontLeft.set(0);
			SwerveDrive.drivingMotorBackRight.set(0);
			SwerveDrive.drivingMotorBackLeft.set(0);
		}
		
		
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	SwerveDrive.drivingMotorFrontRight.set(0);
	SwerveDrive.drivingMotorFrontLeft.set(0);
	SwerveDrive.drivingMotorBackRight.set(0);
	SwerveDrive.drivingMotorBackLeft.set(0);
    }
    
    
    
    
    
    
    
    
    
    
    
}

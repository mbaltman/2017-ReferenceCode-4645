package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.OI;
import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAndShootCommand extends CommandGroup 
{

    public CenterAndShootCommand() 
    {
       
    	
    	int alliance= Robot.allianceConstant;
    	double idealYDistance=0;
    	double idealXDistance=0;
    	double degreeToBoiler=135 * alliance;
    	
        addSequential(new MakeParallel(degreeToBoiler));
        
        
        double[] distanceInformation=(Robot.visionSubsystem.returnBoilerInformation());//updates vision values
    	
        addSequential(new MoveToX(idealXDistance-distanceInformation[0]));//moves in X
       
        distanceInformation=(Robot.visionSubsystem.returnBoilerInformation());//updates vision values
        
    	addSequential(new MoveToY(idealYDistance-distanceInformation[1]));//moves in Y
    	
    	while (OI.leftJoy.getTrigger() || Robot.auto)
    	{
    		Robot.shooterSubsystem.shoot(-475);
    		Robot.reservoirSubsystem.alternate();
    	}
        
    	// this command centers on the boiler 
    }
}

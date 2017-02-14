package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousNextToBoiler extends CommandGroup {

    public AutonomousNextToBoiler() 
    {
      
    	addSequential(new MoveToY(2.5));// drives up to boiler
    	addSequential(new PlaceGearCommand(-1, Robot.allianceConstant));//This will center on the gear system and place it
    	//1 represents the gear next to the boiler
    	
    	//additional positioning?
    	
    	addSequential(new CenterAndShootCommand());//this will center and shoot,  
    	
    	//can have a timer added on to it. Otherwise it will just keep shooting
    }
}

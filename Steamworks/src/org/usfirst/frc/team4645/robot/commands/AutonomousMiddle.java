package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4645.robot.Robot;
import org.usfirst.frc.team4645.robot.RobotMap;

public class AutonomousMiddle extends CommandGroup {

    public AutonomousMiddle() {
       
    	
    	addSequential(new PlaceGearCommand(0, Robot.allianceConstant));//This will center on the gear system and place it
    	//2 represents the gear in the middle
    	//additional positioning?
    	
    	addSequential(new CenterAndShootCommand());//this will center and shoot,  
    	
    	//can have a timer added on to it. Otherwise it will just keep shooting
    	
    	
    	
    }
}

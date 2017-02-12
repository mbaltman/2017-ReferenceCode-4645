package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4645.robot.Robot;
import org.usfirst.frc.team4645.robot.RobotMap;

public class AutonomousMiddle extends CommandGroup {

    public AutonomousMiddle() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
   
    	addSequential(new MoveToY(2));// drives up to boiler
    	addSequential(new PlaceGearCommand(0, Robot.allianceConstant));//This will center on the gear system and place it
    	//2 represents the gear in the middle
    	//additional positioning?
    	
    	addSequential(new CenterAndShootCommand());//this will center and shoot,  
    	
    	//can have a timer added on to it. Otherwise it will just keep shooting
    	
    	
    	
    }
}

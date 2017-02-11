package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousNextToBoiler extends CommandGroup {

    public AutonomousNextToBoiler() {
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
    	
    	
    	addSequential(new MoveToY(2.5));// drives up to boiler
    	addSequential(new PlaceGearCommand(1));//This will center on the gear system and place it
    	//1 represents the gear next to the boiler
    	
    	//additional positioning?
    	
    	addSequential(new CenterAndShootCommand());//this will center and shoot,  
    	
    	//can have a timer added on to it. Otherwise it will just keep shooting
    }
}

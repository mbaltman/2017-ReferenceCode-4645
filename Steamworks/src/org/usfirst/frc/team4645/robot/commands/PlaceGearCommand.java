package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearCommand extends CommandGroup {

    public PlaceGearCommand() {
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
    	
    	//addSequential(new faceThisDirection)
    	//addSequential(Center on this point in X);
    	//addSequential(Center on this point in Y);
    	addSequential(new DropGearCommand());
    	//addSequential(new DriveForwards(Small Distance));
    	addSequential(new PushGearCommand());
    	//addSequential( new Drive Forwards(negativeDistance));
    	
    	//this series of steps will center on the gear, drive forwards, place the gear and then drive backwards
    	//if the robot will need to wait some amount of time before driving backwards then a delay will
    	//need to be added
    	
    }
}

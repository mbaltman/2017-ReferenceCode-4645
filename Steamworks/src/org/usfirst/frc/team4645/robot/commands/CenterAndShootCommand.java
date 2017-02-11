package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAndShootCommand extends CommandGroup {

    public CenterAndShootCommand() {
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
    	double degreeToBoiler=0;
    	double idealYDistance=0;
    	double idealXDistance=0;
    	
        addSequential(new MakeParallel(degreeToBoiler));
        
        
        double[] distanceInformation=(Robot.visionSubsystem.returnBoilerInformation());//updates vision values
    	
        addSequential(new MoveToX(idealXDistance-distanceInformation[0]));//moves in X
       
        distanceInformation=(Robot.visionSubsystem.returnBoilerInformation());//updates vision values
        
    	addSequential(new MoveToY(idealYDistance-distanceInformation[1]));//moves in Y
        addSequential(new ShootCommand());//shoots until interrupted unless command group is called with a timer
        
        
    	// this command centers on the boiler 
    }
}

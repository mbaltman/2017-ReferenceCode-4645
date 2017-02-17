package org.usfirst.frc.team4645.robot.commands;

import org.usfirst.frc.team4645.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearCommand extends CommandGroup 
{

    public PlaceGearCommand(int whichGear,int auto ) 
    {
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
    	
    	
    	
    	double degreeToGear=60 * whichGear * auto;//This depends on which gear spot is being target
    	    	
       	double idealYDistance=0;
    	double idealXDistance=0;
    	
    	
    	addSequential(new MakeParallel(degreeToGear));
    	
    	 double[] distanceInformation=(Robot.visionSubsystem.returnGearInformation());//updates vision values
     	
         addSequential(new MoveToX(idealXDistance-distanceInformation[0]));//moves in X
        
         distanceInformation=(Robot.visionSubsystem.returnGearInformation());//updates vision values
         
     	addSequential(new MoveToY(idealYDistance-distanceInformation[1]));//moves in Y
     	
     //At this point the Robot is Some known distance from the gear and centered on it 
    	
    	
    	addSequential(new MoveToY(1));//how ever many meters to actually place gear
    	
    	addSequential( new MoveToY(-1));
    	
    	addSequential(new ResetGearCommand());
    	
    	
    	//this series of steps will center on the gear, drive forwards, place the gear and then drive backwards
    	//if the robot will need to wait some amount of time before driving backwards then a delay will
    	//need to be added
    	
    }
}

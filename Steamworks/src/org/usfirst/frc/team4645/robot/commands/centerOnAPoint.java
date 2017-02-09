package org.usfirst.frc.team4645.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class centerOnAPoint extends CommandGroup {

    public centerOnAPoint() {
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
    	
    	
    	double pointCamera=0;//degree camera should be at.
    	
        double pointRobot=0;//angle actual robot faces
        
        double directlyLeft=pointRobot +90;
       
        double directlyRight=pointRobot-90;
        
        
    	addSequential(new pointCamera);
    	
    	addSequential(new pointFaceofRobot());
    	addSequential(new centerInX(160));
    	addSequential(move(degree, Robot.Vision));
    }
}

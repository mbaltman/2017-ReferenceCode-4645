package org.usfirst.frc.team4645.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4645.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	
	public static Joystick leftJoy = new Joystick(1);
	Button buttonShoot = new JoystickButton(leftJoy, 1);
	Button buttonIntake = new JoystickButton(leftJoy, 4);
	Button buttonClimb = new JoystickButton(leftJoy, 5);
	
	Button buttonLeftGear = new JoystickButton(leftJoy, 6);
	Button buttonMiddleGear = new JoystickButton(leftJoy, 6);
	Button buttonRightGear = new JoystickButton(leftJoy, 6);
	
	
	
	public static Button resetGyro = new JoystickButton(leftJoy, 7);
	
	public OI()
	{
		
		resetGyro.whenPressed(new ResetGyro());

		
		buttonLeftGear.whenPressed(new PlaceGearCommand(1,1));
	    buttonMiddleGear.whenPressed(new PlaceGearCommand(0,1));
		buttonRightGear.whenPressed(new PlaceGearCommand(-1,1));
		
		buttonShoot.whenPressed(new CenterAndShootCommand());
		buttonIntake.whileHeld(new IntakeCommand());
		buttonClimb.whileHeld(new ClimbCommand());
	
	}

	// button.whileHeld(new ExampleCommand());

	
	// button.whenReleased(new ExampleCommand());
}

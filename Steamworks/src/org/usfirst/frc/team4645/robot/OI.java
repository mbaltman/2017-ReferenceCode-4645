package org.usfirst.frc.team4645.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4645.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick leftJoy = new Joystick(1);
	Button button1 = new JoystickButton(leftJoy, 1);
	Button button2 = new JoystickButton(leftJoy, 3);
	Button button3 = new JoystickButton(leftJoy, 4);
	Button button4 = new JoystickButton(leftJoy, 5);
	Button button5 = new JoystickButton(leftJoy, 6);
	Button button7 = new JoystickButton(leftJoy, 7);
	Button button8 = new JoystickButton(leftJoy, 8);
	
	public OI(){
	    
		button1.whileHeld(new IntakeCommand());
		
		button2.whenPressed(new ReservoirCommand());
		
		
		button3.whileHeld(new ClimbCommand());
		
		button4.whileHeld(new PushGearCommand());
		
		button5.whileHeld(new DropGearCommand());
	    
		button7.whileHeld(new testValuesVision());
	
	}
	// button.whenPressed(new ExampleCommand());

	
	// button.whileHeld(new ExampleCommand());

	
	// button.whenReleased(new ExampleCommand());
}

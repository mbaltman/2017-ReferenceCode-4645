
package org.usfirst.frc.team4645.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class Robot extends IterativeRobot {
  
	CANTalon motorOne = new CANTalon(2);	
	CANTalon motorTwo = new CANTalon(1);
	CANTalon motorThree = new CANTalon(3);
	CANTalon motorFour = new CANTalon(4);
	Joystick joy1 = new Joystick(0);	
	public void robotInit() {
        /* first choose the sensor */
        
	}  
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
      
//        
//        
//        
		if (joy1.getRawButton(3))
		{
			
			motorTwo.set(100);
			
			//motorTwo.set(1);			
		}
		else
		{
			motorOne.set(0);
			motorTwo.set(0);
			motorThree.set(0);
		}
		
		
		}
	
//        
//       
//		
//		
//        SmartDashboard.putNumber("velocity", shooter.getEncVelocity());
//    	SmartDashboard.putNumber("motoroutput", shooter.getOutputVoltage());
//    	SmartDashboard.putNumber("current", shooter.getOutputCurrent());
        
    }

/**
 * Example demonstrating the velocity closed-loop servo.
 * Tested with Logitech F350 USB Gamepad inserted into Driver Station]
 * 
 * Be sure to select the correct feedback sensor using SetFeedbackDevice() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick 
 * to throttle the Talon manually.  This will confirm your hardware setup.
 * Be sure to confirm that when the Talon is driving forward (green) the 
 * position sensor is moving in a positive direction.  If this is not the cause
 * flip the boolena input to the SetSensorDirection() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * use the button shortcuts to servo to target velocity.  
 *
 * Tweak the PID gains accordingly.
 */
package org.usfirst.frc.team4645.robot;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class Robot extends IterativeRobot {
  
	CANTalon shooter = new CANTalon(0);	
	CANTalon pgMotor = new CANTalon(1);
	Joystick _joy = new Joystick(0);	
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	
	public void robotInit() {
        /* first choose the sensor */
        shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        //shooter.reverseSensor(false);
        //shooter.configEncoderCodesPerRev(80); // if using FeedbackDevice.QuadEncoder
        //shooter.configPotentiometerTurns(XXX), // if using FeedbackDevice.AnalogEncoder or AnalogPot

        /* set the peak and nominal outputs, 12V means full */
        shooter.configNominalOutputVoltage(+0.0f, -0.0f);
        shooter.configPeakOutputVoltage(+0.0f, -12.0f);
        /* set closed loop gains in slot0 */
        shooter.setProfile(1);
        
        shooter.setF(1.557);
        shooter.setP(4.092);
        shooter.setI(0); 
        shooter.setD(81.84);
	}  
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        if(_joy.getTrigger()){
        	
        	/* Speed mode */
        	double targetSpeed = -475;
        	shooter.changeControlMode(TalonControlMode.Speed);
        	shooter.set(targetSpeed); 
        	//pgMotor.set(-1);
        }
        else {
        	shooter.set(0);
        	//pgMotor.set(0);
        }
        
        if(shooter.getEncVelocity() < -475) {
        	pgMotor.set(-1);
        }
        else {
        	pgMotor.set(0);
        }
        
        SmartDashboard.putNumber("velocity", shooter.getEncVelocity());
    	SmartDashboard.putNumber("motoroutput", shooter.getOutputVoltage());
    	SmartDashboard.putNumber("current", shooter.getOutputCurrent());
        
    }
}












package org.usfirst.frc.team4645.robot.subsystems;

import org.usfirst.frc.team4645.robot.OI;
import org.usfirst.frc.team4645.robot.Robot;
import org.usfirst.frc.team4645.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
/**
 *
 */

public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon shooter = RobotMap.shooter1;
	public CANTalon pgMotor = RobotMap.pgMotor;
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	Joystick _joy = OI.leftJoy;
	double targetSpeed=Robot.shooterSpeed;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
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
   
    public void setShooterSpeed()
    {
    	shooter.changeControlMode(TalonControlMode.Speed);
    	shooter.set(targetSpeed);
    }
    public void shooterStop()
    {
    	shooter.set(0);
    	pgMotor.set(0);
    }
    public void pgMotorMove()
    {
    	pgMotor.set(-1);
    }
   
    public void pgMotorStop()
    {
    	pgMotor.set(0);
    }
    
}


package xbot.edubot.subsystems.drive;

import xbot.common.controls.*;
import xbot.common.controls.actuators.XSpeedController;
import xbot.common.controls.sensors.DistanceSensor;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.edubot.rotation.MockHeadingSensor;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import edu.wpi.first.wpilibj.SpeedController;

@Singleton
public class DriveSubsystem {
	boolean toggle = false;
	public MockDistanceSensor distanceSensor;
	public MockHeadingSensor gyro;
	
	XSpeedController frontLeft;
	XSpeedController frontRight;
	XSpeedController rearLeft;
	XSpeedController rearRight;
		
	@Inject
	public DriveSubsystem(WPIFactory factory) {
		// instantiate speed controllers and sensors here, save them as class members
		distanceSensor = new MockDistanceSensor();
		gyro = new MockHeadingSensor();
		
		frontLeft = factory.getSpeedController(1);
		rearLeft = factory.getSpeedController(3);
		frontRight = factory.getSpeedController(2);
		rearRight = factory.getSpeedController(4);
	}
	
	public void tankDrive(double leftPower, double rightPower) {
		// You'll need to take these power values and assign them to all of the motors. As
		// an example, here is some code that has the frontLeft motor to spin according to
		// the value of leftPower:
		if(toggle == true){
			leftPower /= 2;
			rightPower /= 2;
		}
		frontLeft.set(leftPower);
		rearLeft.set(leftPower);
		frontRight.set(rightPower);
		rearRight.set(rightPower);
	}
	public void arcadeDrive(double leftValueY, double leftValueX){
		double finalLeft = leftValueX + leftValueY;
		double finalRight = -leftValueX + leftValueY;
		if(finalLeft > 1){
			finalLeft = 1;
		}
		else if(finalLeft < -1){
			finalLeft = -1;
		}
		if(finalRight > 1) {
			finalRight = 1;
		}
		else if (finalRight < -1){
			finalRight = -1;
		}
		frontLeft.set((finalLeft));
		rearLeft.set(finalLeft);
		frontRight.set(finalRight);
		rearRight.set(finalRight);
		
			
	
	}
	public void togglePrecisionMode(){
		toggle = !toggle;
	}
}

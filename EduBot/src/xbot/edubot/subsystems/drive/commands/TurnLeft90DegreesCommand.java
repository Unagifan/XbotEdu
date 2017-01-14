package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
	
	DriveSubsystem drive;
	double hope;
	boolean done = false;
	double previousAngle;
	
	@Inject
	public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		 hope = drive.gyro.getYaw() + 90;
		 if(hope < 0){
			 hope = hope + 360;
		 }
		 
	}
	public void rotate(double goal,double difference){
		double angle = drive.gyro.getYaw();
		if(angle < 0){
			angle = angle + 360;
		}
		if(goal == 0){
			angle = drive.gyro.getYaw();
		}
		
		double error = goal-angle;
		drive.tankDrive(error/difference * -.1,error/difference * 0.1);
		if(Math.abs(error) <= 0.05){
			drive.tankDrive(0, 0);
			if(Math.abs(previousAngle - angle) <= 0.1){
				done = true;
			}
		}
		previousAngle = angle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		rotate(hope,90);
		
	}
	public boolean isFinished(){
		return done;
	}
}





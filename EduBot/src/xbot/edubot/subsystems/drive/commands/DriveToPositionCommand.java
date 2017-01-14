package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

	DriveSubsystem drive;
	double setPosition;
	double perOneVolt;
	boolean finishedOrNot =  false;
	
	
	@Inject
	public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	public void setTargetPosition(double position) {
		setPosition = position;
		// This method will be called by the test, and will give you a goal distance.
		// You'll need to remember this target position and use it in your calculations.
	}
	
	@Override
	public void initialize() {
		// If you have some one-time setup, do it here.
		//How much it travels with a volt jolt
	}

	@Override
	public void execute() {
		// Here you'll need to figure out a technique that:
		// - Gets the robot to move to the target position
		double distance = drive.distanceSensor.getDistance();
		double error = Math.abs(setPosition - distance);
		drive.tankDrive(error/setPosition *.1, error/setPosition*.1 );
		// - Gets the robot stop (or at least be moving really really slowly) at the target position
		// How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
		if(error <= 0.02 || distance > setPosition ){
			finishedOrNot = true;
		}
	}
	
	@Override
	public boolean isFinished() {
		// Modify this to return true once you have met your goal, 
		// and you're moving fairly slowly (ideally stopped)
		return finishedOrNot;
	}

}

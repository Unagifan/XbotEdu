package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
	TurnLeft90DegreesCommand turn;
	DriveSubsystem drive;
	double goal;
	double start;
	double difference;
	boolean done;
	double previousAngle;
	
	@Inject
	public DriveToOrientationCommand(DriveSubsystem driveSubsystem,TurnLeft90DegreesCommand turn) {
		this.drive = driveSubsystem;
		this.turn = turn;
	}
	
	public void setTargetHeading(double heading) {
		// This method will be called by the test, and will give you a goal heading.
		// You'll need to remember this target position and use it in your calculations.
		goal = heading;
		if(goal < 0){
			goal = goal + 360;
		}
	}
	
	@Override
	public void initialize() {
		// If you have some one-time setup, do it here.
		start = drive.gyro.getYaw();
		if(start < 0){
			start = start + 360;
		}
	}

	@Override
	public void execute() {
		double angle = drive.gyro.getYaw();
		if(angle < 0){
			angle = angle + 360;
		}
		if(goal == 0){
			angle = drive.gyro.getYaw();
		}
		// Here you'll need to figure out a technique that:
		// - Gets the robot to turn to the target orientation
		// - Gets the robot stop (or at least be moving really really slowly) at the target position
		difference = goal - start;
		if(Math.abs(goal - angle) <= 0.1){
			drive.tankDrive(0, 0);
			if(Math.abs(angle - previousAngle) <= 0.1){
				done = true;
			}
		}else{
			turn.rotate(goal, difference);
		}
		
		previousAngle = angle;
		
		// How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
	}

	@Override
	public boolean isFinished() {
		// Modify this to return true once you have met your goal, 
		// and you're moving fairly slowly (ideally stopped)
		return done;
	}
}

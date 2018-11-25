package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class VariableTurn extends Command {
	private long timeStarted;
	private int turnAngle;
	public VariableTurn() {
		SmartDashboard.putString("Turn angle", "0");
	}
	protected void initialize() {
		String angleString = SmartDashboard.getString("Turn angle", "0");
		try {
			turnAngle = Integer.parseInt(angleString);	// Retrieve an angle from the SmartDashboard, input by user, only if it's a valid angle.
		} catch (NumberFormatException error) {
			turnAngle = 0;
		}
		timeStarted = System.currentTimeMillis();
	}
	
	protected void execute() {
		if (System.currentTimeMillis() - timeStarted < 0.025 * turnAngle) {	// Turn at a constant rate (determined through experimentation) to get to angle.
			Robot.driveTrain.setDrive(Math.signum(turnAngle) * 0.7, -1 * Math.signum(turnAngle) * 0.7);
		} else {
			end();
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.driveTrain.stop();
	}
	
	protected void interrupted() {
		end();
	}
}

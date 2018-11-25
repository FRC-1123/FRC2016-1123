package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.
// Never really worked, probably due to isFinished method or implementation of SmartDashboard.

public class EmergencyStop extends Command {
	private double timeStarted;
	protected void initialize() {
		timeStarted = System.currentTimeMillis();
	}

	protected void execute() {
		if (System.currentTimeMillis() - timeStarted < 1000) {   // Stops all systems for about a second.
			Robot.driverControl = false;  // driverControl determines whether the controller does anything or not.
			Robot.driveTrain.stop();
			Robot.wedge.stop();
		} else {
			end();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.driverControl = true;
	}

	protected void interrupted() {
		end();
	}
}
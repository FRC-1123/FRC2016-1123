package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class MoveBall extends Command {   // Command that calls takeXBoxInput method, used in setting default action for subsystems.
	public MoveBall() {  // Similar to DriveWithJoystick.
		requires(Robot.ballIntake);
	}

	protected void initialize() {
		//
	}
	
	protected void execute() {
		Robot.ballIntake.takeXBoxInput(Robot.oi.xBoxController);
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
      Robot.ballIntake.stop();
	}

	protected void interrupted() {
		end();
	}
}

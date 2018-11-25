package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class LeftTurn extends Command {
	private double leftValue;
	private double rightValue;
   public LeftTurn() {
      setTimeout(1);
   }
   
	protected void initialize() {
		leftValue = Robot.driveTrain.getLeftDrive(); // Store current movement of drivetrain.
		rightValue = Robot.driveTrain.getRightDrive();
	}
	
	protected void execute() {
		Robot.driverControl = false;  // Don't allow driver influence during maneuver .
		Robot.driveTrain.setDrive(0, -0.25);	// Negative right drive value: forwards.
	}
	
	protected boolean isFinished() {
		return isTimedOut();
	}
   
	protected void end() {
		Robot.driveTrain.setLeftDrive(leftValue); // Restore previous movement of drivetrain.
		Robot.driveTrain.setRightDrive(rightValue);
		Robot.driverControl = true;   // Allow driver to take control once again.
	}
   
	protected void interrupted() {
		end();
	}
}
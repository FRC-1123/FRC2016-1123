package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class DriveWithJoystick extends Command {   // Command that calls takeXBoxInput method, used in setting default action for subsystems.
	public DriveWithJoystick() {
		requires(Robot.driveTrain);
	}

	protected void initialize() {
		//
	}
   
	protected void execute() {
		if (Robot.driverControl) { // If the robot isn't doing a turn or emergency-stopped (our version of it), allow the driver to control the drivetrain.
			Robot.driveTrain.takeXBoxInput(Robot.oi.xBoxController);
		}
	}

	protected boolean isFinished() {
      return false;
   }

	protected void end() {
      //
   } 

	protected void interrupted() {
      end();
   }
}

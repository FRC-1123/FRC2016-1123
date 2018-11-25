package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

// Version 2.0 of autonomous, allows for on-site autonomous decisions.

public class AutonomyConfigurable extends Command {
	private long timeStarted;
	private Defense defenseType;
	public AutonomyConfigurable() {
      DigitalInput port0 = new DigitalInput(0); // Initialize ports 0 through 3 on digital input switch on robot.
      DigitalInput port1 = new DigitalInput(1);
      DigitalInput port2 = new DigitalInput(2);
      DigitalInput port3 = new DigitalInput(3);
      defenseType = Defense.DEFAULT;   // Do nothing if nothing selected on switch.
      if (!port0.get()) {  // First port represents lowbar/portcullis choice.
         defenseType = Defense.LOWBAR;
      } else if (!port1.get()) { // Second port represents moat/rough terrain/rockwall/ramparts choice.
         defenseType = Defense.MOAT;
      } else if (!port2.get()) { // Third port represents chival de frise choice.
         defenseType = Defense.FRISE;
      } else if (!port3.get()) { // Fourth port represents shooting choice (never used).
         defenseType = Defense.SHOOT;
      }
	}

	protected void initialize() {
		timeStarted = System.currentTimeMillis();
	}

	protected void execute() {
      // Similar code to that of first version, with slight variances in timings/power.
      // See AutonomyBarrier.java for explanations.
		switch (defenseType) {
			case LOWBAR:
			case PORTCULLIS:
				if (System.currentTimeMillis() - timeStarted < 1750) {
					Robot.wedge.setMovement(0.6);
				} else if (System.currentTimeMillis() - timeStarted < 7000) {
					Robot.wedge.stop();
					Robot.driveTrain.setDrive(0.55, -0.55);
				} else if (System.currentTimeMillis() - timeStarted < 9000) {
					Robot.driveTrain.stop();
					Robot.wedge.setMovement(-0.7);
				} else {
					end();
				}
				break;
			case MOAT:
			case TERRAIN:
			case ROCKWALL:
			case RAMPARTS:
				if (System.currentTimeMillis() - timeStarted < 3500) {
					Robot.driveTrain.setDrive(-0.8, 0.8);
				} else {
					end();
				}
				break;
			case FRISE:
				if (System.currentTimeMillis() - timeStarted < 1500) {
					Robot.driveTrain.setDrive(0.5, -0.5);
				} else if (System.currentTimeMillis() - timeStarted < 4000) {
					Robot.wedge.setMovement(0.6);
					Robot.driveTrain.stop();
				} else if (System.currentTimeMillis() - timeStarted < 7500) {
					Robot.wedge.stop();
					Robot.driveTrain.setDrive(0.925, -0.925);
				} else {
					end();
				}
				break;
			case SHOOT:
				if (System.currentTimeMillis() - timeStarted < 1750) {
					Robot.wedge.setMovement(0.6);
				} else if (System.currentTimeMillis() - timeStarted < 6000) {
					Robot.wedge.stop();
					Robot.driveTrain.setDrive(0.55, -0.55);
				} else if (System.currentTimeMillis() - timeStarted < 9000) {
					Robot.driveTrain.setDrive(0.85, 0);
					Robot.wedge.setMovement(-0.7);
				} else if (System.currentTimeMillis() - timeStarted < 12000) {
					Robot.driveTrain.setDrive(0.55, -0.55);
				} else if (System.currentTimeMillis() - timeStarted < 12000) {
					Robot.driveTrain.stop();
				} else if (System.currentTimeMillis() - timeStarted < 15000) {
					Robot.ballIntake.setMovement(-1.0);
				} else {
					end();
				}
				break;
         default:
            break;
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.wedge.stop();
		Robot.driveTrain.stop();
		Robot.ballIntake.stop();
	}

	protected void interrupted() {
		end();
	}

	private enum Defense {
		LOWBAR, PORTCULLIS, FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLYPORT, ROCKWALL, TERRAIN, SHOOT, DEFAULT;
	}
}
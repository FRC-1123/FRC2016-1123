package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class WedgeDown extends Command {
   private double tempRate;
   private double timeStarted;
   public WedgeDown() {
      tempRate = 0.5;
   }

   protected void initialize() {
   	timeStarted = System.currentTimeMillis();
   }

   protected void execute() {
   	if (System.currentTimeMillis() - timeStarted < 250) { // Moves wedge down for 0.25 seconds.
         Robot.wedge.setMovement(-1 * tempRate);
	  } else {
		  end();
	  }
   }

   protected boolean isFinished() {
      return (System.currentTimeMillis() - timeStarted < 250);
   }

   protected void end() {
      Robot.wedge.stop();
   }

   protected void interrupted() {
      end();
   }
}
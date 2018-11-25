package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class WedgeUp extends Command {
   private double tempRate;
   private double timeStarted;
   public WedgeUp() {
      tempRate = 0.5;
   }

   protected void initialize() {
      timeStarted = System.currentTimeMillis();
   }

   protected void execute() {
	  if (System.currentTimeMillis() - timeStarted < 250) {  // Moves wedge up for 0.25 seconds.
         Robot.wedge.setMovement(tempRate);
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
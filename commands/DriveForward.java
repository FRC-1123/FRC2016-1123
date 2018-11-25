package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class DriveForward extends Command {
   private double tempRate;
   private double timeStarted;
   public DriveForward() {
      tempRate = 0.4;
   }

   protected void initialize() {
      timeStarted = System.currentTimeMillis();
   }

   protected void execute() {
      if (System.currentTimeMillis() - timeStarted < 2000) {
         Robot.driveTrain.setDrive(tempRate, -1 * tempRate);
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
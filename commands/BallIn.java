package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1123.robot.Robot;

/**
 * Name: IronCrystal
 * Date: 4/27/2016 @ 11:05 AM
 */
// Mostly self-explanatory, see series of Autonomy commands for explanations on methods.

public class BallIn extends Command {
   protected void initialize() {
      //
   }

   protected void execute() {
      Robot.ballIntake.setMovement(1.0);
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

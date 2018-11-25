package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class RightTurn extends Command {  // Similar to LeftTurn.
   private double leftValue;
   private double rightValue;
   public RightTurn() {
      setTimeout(1);
   }

   protected void initialize() {
      leftValue = Robot.driveTrain.getLeftDrive();
      rightValue = Robot.driveTrain.getRightDrive();
   }

   protected void execute() {
      Robot.driverControl = false;
      Robot.driveTrain.setDrive(0.25, 0); // Positive left drive value: forwards.
   }

   protected boolean isFinished() {
      return isTimedOut();
   }

   protected void end() {
      Robot.driveTrain.setDrive(leftValue, rightValue);
      Robot.driverControl = true;
   }

   protected void interrupted() {
      end();
   }
}
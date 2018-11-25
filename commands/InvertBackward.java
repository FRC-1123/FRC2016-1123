package org.usfirst.frc.team1123.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1123.robot.subsystems.DriveTrain;

// Test class, not used.
// Mostly self-explanatory, see series of Autonomy commands for explanations on method use.

public class InvertBackward extends Command {
   private double timeStarted;
   public InvertBackward() {
      //
   }
   protected void initialize() {
      timeStarted = System.currentTimeMillis();
      DriveTrain.invertMultiplier = -1;
      SmartDashboard.putString("Joystick direction", "REVESE (WEDGE)");
   }
   protected void execute() {
      if (System.currentTimeMillis() - timeStarted > 250) {
         end();
      }
   }
   protected boolean isFinished() {
      return (System.currentTimeMillis() - timeStarted > 250);
   }
   protected void end() {
      //
   }
   protected void interrupted() {
      //
   }
}
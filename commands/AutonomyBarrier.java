package org.usfirst.frc.team1123.robot.commands;

import org.usfirst.frc.team1123.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Version 1.0 of autonomous.

public class AutonomyBarrier extends Command {
   private long timeStarted;
   private Defense defenseType;
   public AutonomyBarrier() {
      defenseType = Defense.MOAT;	// Current defense to traverse.
   }

   protected void initialize() {
      timeStarted = System.currentTimeMillis();	// When autonomous mode starts.
   }

   protected void execute() {
      switch (defenseType) {
         case LOWBAR:   // For both lowbar and portcullis, execute the following.
         case PORTCULLIS:
            if (System.currentTimeMillis() - timeStarted < 1750) {   // From 0 to 1.75 seconds, move the wedge down at 60% power.
               Robot.wedge.setMovement(0.6);
            } else if (System.currentTimeMillis() - timeStarted < 3000) {	// From 1.75 to 3 seconds, stop wedge movement, wait.
               Robot.wedge.stop();
            } else if (System.currentTimeMillis() - timeStarted < 7000) {  // From 3 to 7 seconds, drive forward at 55% power.
               Robot.driveTrain.setDrive(0.55, -0.55);
            } else if (System.currentTimeMillis() - timeStarted < 9000) {	// From 7 to 9 seconds, stop driving forward, lift up wedge at 60% power.
               Robot.driveTrain.stop();
               Robot.wedge.setMovement(-0.6);
            } else { // Stop after 9 seconds.
               end();
            }
            break;
         case FRISE: // For chival de frise, execute the following.
            if (System.currentTimeMillis() - timeStarted < 1500) {	// From 0 to 1.5 seconds, drive forward at 25%.
               Robot.driveTrain.setDrive(0.25, -0.25);
            } else if (System.currentTimeMillis() - timeStarted < 4000) {	// From 1.5 to 4 seconds, stop driving forward, move the wedge down at 60%.
               Robot.driveTrain.stop();
               Robot.wedge.setMovement(0.6);
            } else if (System.currentTimeMillis() - timeStarted < 6000) {  // From 4 to 6 seconds, stop wedge movement, drive forward at 60%.
               Robot.driveTrain.setDrive(0.6, -0.6);
               Robot.wedge.stop();
            } else { // Stop after 6 seconds.
               end();
            }
            break;
         case MOAT:  // For moat, rough terrain, rockwall, and ramparts, execute the following.
         case TERRAIN:
         case ROCKWALL:
         case RAMPARTS:
            if (System.currentTimeMillis() - timeStarted < 3500) {   // From 0 to 3.5 seconds, drive backwards at 80%.
               Robot.driveTrain.setDrive(-0.8, 0.8);
            } else { // Stop after 3.5 seconds.
               end();
            }
            break;
         case DRAWBRIDGE:  // For drawbridge and sallyport, do nothing.
         case SALLYPORT:
            break;
         case SHOOT: // Specialized lowbar task.
            if (System.currentTimeMillis() - timeStarted < 1750) {
               Robot.wedge.setMovement(0.6);
            } else if (System.currentTimeMillis() - timeStarted < 3000) {
               Robot.wedge.stop();
            } else if (System.currentTimeMillis() - timeStarted < 8000) {  // Similar code to lowbar to this point.
               Robot.driveTrain.setDrive(0.55, -0.55);
            } else if (System.currentTimeMillis() - timeStarted < 9500) {  // From 8 to 9.5 seconds, turn left (left drive at 70%, right drive at 0%), while moving wedge up at 60%.
               Robot.driveTrain.setDrive(0.7, 0);
               Robot.wedge.setMovement(-0.6);
            } else if (System.currentTimeMillis() - timeStarted < 12500) { // From 9.5 to 12.5 seconds, stop moving wedge, drive forward at 50%.
               Robot.driveTrain.setDrive(0.55, -0.55);
               Robot.wedge.stop();
            } else if (System.currentTimeMillis() - timeStarted < 13000) { // From 12.5 to 13 seconds, stop driving forward.
               Robot.driveTrain.stop();
            } else if (System.currentTimeMillis() - timeStarted < 15000) { // From 13 to 15 seconds, release ball.
               Robot.ballIntake.setMovement(-1.0);
            } else { // Stop after 15 seconds/end of autonomous (should stop automatically during matches, placed here for testing/consistency).
               end();
            }
            break;
      }
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {  // Called when autonomous is over.
      Robot.wedge.stop();
      Robot.driveTrain.stop();
      Robot.ballIntake.stop();
   }

   protected void interrupted() {   // If autonomous is interrupted, do same as when autonomous is over.
      end();
   }

   private enum Defense {
      LOWBAR, PORTCULLIS, FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLYPORT, ROCKWALL, TERRAIN, SHOOT;
   }
}
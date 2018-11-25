package org.usfirst.frc.team1123.robot;

import org.usfirst.frc.team1123.robot.commands.AutonomyConfigurable;
import org.usfirst.frc.team1123.robot.commands.EmergencyStop;
import org.usfirst.frc.team1123.robot.commands.VariableTurn;
import org.usfirst.frc.team1123.robot.subsystems.BallIntake;
import org.usfirst.frc.team1123.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1123.robot.subsystems.Wedge;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// (IMPORTANT) FRC's standard instructions/comments = bulk comment or the comments with "*".

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
   public static Command autonomous;
   public static OI oi;
   public static DriveTrain driveTrain;
   public static Wedge wedge;
   public static BallIntake ballIntake;
   public static CameraServer camera;
   public static boolean driverControl = true;
   public static long timeStartedAuto, timeStartedTele;

   /**
    * This function is run when the robot is first started up and should be
    * used for any initialization code.
    */
   public void robotInit() {
      RobotMap.init();  // Initialize RobotMap and subsystems.
      driveTrain = new DriveTrain();
      wedge = new Wedge();
      ballIntake = new BallIntake();

      /**
       * OI must be constructed after subsystems. If the OI creates Commands
       * (which it very likely will), subsystems are not guaranteed to be
       * constructed yet. Thus, their requires() statements may grab null
       * pointers. Bad news. Don't move it.
       */

      oi = new OI();

      /** the camera name (ex "cam0") can be found through the roborio web interface */
      camera = CameraServer.getInstance();
      camera.setQuality(1);   // As value increases, loss during compression increases/image quality decreases.
      camera.startAutomaticCapture("cam0");

      /** instantiate the command used for the autonomous period */
      autonomous = new AutonomyConfigurable();

      SmartDashboard.putData(Scheduler.getInstance());

      // Commands placed on SmartDashboard for easy access/testing purposes.
      SmartDashboard.putData("EMERGENCY STOP", new EmergencyStop());
      SmartDashboard.putData("Variable Turn", new VariableTurn());
//      SmartDashboard.putData("Drive Backward (2 seconds)", new DriveBackward());
//      SmartDashboard.putData("Drive Forward (2 seconds)", new DriveForward());
//      SmartDashboard.putData("Wedge Down", new WedgeDown());
//      SmartDashboard.putData("Wedge Up", new WedgeUp());
//      SmartDashboard.putData("Left turn", new LeftTurn());
//      SmartDashboard.putData("Right turn", new RightTurn());
//      SmartDashboard.putData("U-turn (L)", new UTurnL());
//      SmartDashboard.putData("U-turn (R)", new UTurnR());
//      SmartDashboard.putData("Invert joystick controls", new InvertMovement());
//      SmartDashboard.putString("Inversion multiplier", "" + DriveTrain.invertMultiplier);
//      SmartDashboard.putData("Ball In", new BallIn());
//      SmartDashboard.putData("Ball Out", new BallOut());
   }

   /**
    * This function is called when the disabled button is hit.
    * You can use it to reset subsystems before shutting down.
    */
   public void disabledInit(){
      // No subsystems to reset, so left blank.
   }

   public void disabledPeriodic() {
      Scheduler.getInstance().run();
   }

   public void autonomousInit() {   // Starts autonomous when called.
      if (autonomous != null) {
         timeStartedAuto = System.currentTimeMillis();
         autonomous.start();
      }
   }

   /** This function is called periodically during autonomous */
   public void autonomousPeriodic() {  // Should be called once every 20 ms?
      SmartDashboard.putNumber("Game Timer", (150000 - (System.currentTimeMillis() - timeStartedAuto)) / 1000);
      Scheduler.getInstance().run();
   }

   /**
    * This makes sure that the autonomous stops running when
    * teleop starts running. If you want the autonomous to
    * continue until interrupted by another command, remove
    * this line or comment it out.
    */
   public void teleopInit() {
      if (autonomous != null) {
         timeStartedTele = System.currentTimeMillis();
         autonomous.cancel();
      }
   }

   /** This function is called periodically during operator control */
   public void teleopPeriodic() {   // Should also be called once every 20 ms, or however periodic autonomousPeriodic() is called.
      SmartDashboard.putNumber("Game Timer", (135000 - (System.currentTimeMillis() - timeStartedTele)) / 1000);
      Scheduler.getInstance().run();
   }

   /** This function is called periodically during test mode */
   public void testPeriodic() {  // Never used during season, should probably find out what it is in future.
      LiveWindow.run();
   }
}

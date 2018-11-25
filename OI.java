package org.usfirst.frc.team1123.robot;

import edu.wpi.first.wpilibj.Joystick;

// (IMPORTANT) FRC's standard instructions/comments = bulk comment or the comments with "*".

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   /** CREATING BUTTONS
    * One type of button is a joystick button which is any button on a joystick.
    * You create one by telling it which joystick it's on and which button
    * number it is.
    * Joystick stick = new Joystick(port);
    * Button button = new JoystickButton(stick, buttonNumber);
    *
    * There are a few additional built in buttons you can use. Additionally,
    * by subclassing Button you can create custom triggers and bind those to
    * commands the same as any other Button.
    *
    ** TRIGGERING COMMANDS WITH BUTTONS
    * Once you have a button, it's trivial to bind it to a button in one of
    * three ways:
    *
    * Start the command when the button is pressed and let it run the command
    * until it is finished as determined by its isFinished method.
    * button.whenPressed(new ExampleCommand());
    *
    * Run the command while the button is being held down and interrupt it once
    * the button is released.
    * button.whileHeld(new ExampleCommand());
    *
    * Start the command when the button is released and let it run the command
    * until it is finished as determined by it's isFinished method.
    * button.whenReleased(new ExampleCommand());
    */

   public Joystick xBoxController;
//    public Button stopButton, fInvertButton, bInvertButton;
   public OI() {
//       Button mapping: http://wiki.unity3d.com/images/a/a7/X360Controller2.png
//       Axis mapping: http://www.chiefdelphi.com/forums/showpost.php?p=1429607&postcount=4
      xBoxController = new Joystick(0);  // Not technically a joystick, simply acts as a container for all the buttons and joysticks.

//       stopButton = new JoystickButton(xBoxController, 7);   // The select button on the xBox controller.
//       stopButton.whileHeld(new EmergencyStop()); // When HELD, executes the given command, EmergencyStop, (PRESSED for the others below).
//
//       fInvertButton = new JoystickButton(xBoxController, 4);
//       fInvertButton.whenPressed(new InvertForward());
//
//       bInvertButton = new JoystickButton(xBoxController, 1);
//       bInvertButton.whenPressed(new InvertBackward());
//
//       Example button mappings, numbers for buttons may not be accurate.
   }
}


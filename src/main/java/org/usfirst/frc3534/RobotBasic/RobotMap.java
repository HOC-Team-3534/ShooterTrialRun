package org.usfirst.frc3534.RobotBasic;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static WPI_TalonFX frontLeftMotor;		//1
	//spare talon srx								//2
	public static WPI_TalonFX backLeftMotor;		//3
	//spare talon srx								//4
	//spare talon srx								//5
	//spare talon srx								//6
	//spare talon srx								//7
	public static WPI_TalonFX backRightMotor;		//8
	//spare talon srx								//9
	public static WPI_TalonFX frontRightMotor;		//10
	public static WPI_TalonFX shooter; 				//11
	public static WPI_TalonSRX topBelt;				//12
	public static WPI_TalonSRX indexWheel;				//13
	//public static WPI_TalonSRX bottomBelt;				//14
	public static WPI_TalonFX shooterSlave;

	public static ColorSensorV3 colorSensor;

	public static Spark blinkin;

	/** EXAMPLE public static DoubleSolenoid elevatorCylinderOne;		//first value -> PCM A, CHANNEL 0, 1 */


	public static AHRS navx;

	public static final double maxVelocity = 6.6; //meters per second
	public static final double maxAngularVelocity = Math.PI * 6; //radians per second

	// Wheel Encoder Calculations
	public static final double typicalAcceleration = 1.0; //meters per second per second
	public static final double robotMass = 50; //kg
	public static final double wheelDiameter = .1524; // measured in meters
	public static final double gearRatio = 1/7.71;
	public static final int ticksPerMotorRotation = 2048; // 2048 for Falcon500 (old ecnoders 1440 if in talon, 360 if into roboRIO)
	public static final double driveWheelTorque = (wheelDiameter / 2) * (robotMass / 4 * typicalAcceleration) * Math.sin(90);
	public static final double falconMaxRPM = 6380; //- 1 / 0.0007351097 * driveWheelTorque;
	public static final double maxTicksPer100ms = falconMaxRPM * ticksPerMotorRotation / 60 / 10;
	public static final double distancePerMotorRotation = gearRatio * wheelDiameter * Math.PI;
	public static final double encoderVelocityToWheelVelocity =  ticksPerMotorRotation / 10 / distancePerMotorRotation; //encoder ticks per 100ms to meters per second
	//public static final double inchesPerCountMultiplier = wheelDiameter * Math.PI / ticksPerRotation;
	//public static final double codesPer100MillisToInchesPerSecond = inchesPerCountMultiplier * 10;

	public static void init() {

		// frontLeftMotor = new WPI_TalonFX(1);
		// // frontLeftMotor.configFactoryDefault(0);
		// // frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// // frontLeftMotor.setSensorPhase(true);
		// // frontLeftMotor.setInverted(false);
		// // frontLeftMotor.configNominalOutputForward(0, 0);
		// // frontLeftMotor.configNominalOutputReverse(0, 0);
		// // frontLeftMotor.configPeakOutputForward(1, 0);
		// // frontLeftMotor.configPeakOutputReverse(-1, 0);
		// frontLeftMotor.config_kF(0, 0.05, 0);
		// frontLeftMotor.config_kP(0, 3, 0);
		// frontLeftMotor.config_kI(0, 0, 0);
		// frontLeftMotor.config_kD(0, 80, 0);
		// frontLeftMotor.setNeutralMode(NeutralMode.Brake);

		// backLeftMotor = new WPI_TalonFX(3);
		// //backLeftMotor.configFactoryDefault(0);
		// //backLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// //backLeftMotor.setSensorPhase(true);
		// //backLeftMotor.setInverted(false);
		// //backLeftMotor.configNominalOutputForward(0, 0);
		// //backLeftMotor.configNominalOutputReverse(0, 0);
		// //backLeftMotor.configPeakOutputForward(1, 0);
		// //backLeftMotor.configPeakOutputReverse(-1, 0);
		// backLeftMotor.config_kF(0, 0.05, 0);
		// backLeftMotor.config_kP(0, 3, 0);
		// backLeftMotor.config_kI(0, 0, 0);
		// backLeftMotor.config_kD(0, 80, 0);
		// backLeftMotor.setNeutralMode(NeutralMode.Brake);

		// backRightMotor = new WPI_TalonFX(8);
		// //backRightMotor.configFactoryDefault(0);
		// //backRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// //backRightMotor.setSensorPhase(true);
		// backRightMotor.setInverted(true);
		// //backRightMotor.configNominalOutputForward(0, 0);
		// //backRightMotor.configNominalOutputReverse(0, 0);
		// //backRightMotor.configPeakOutputForward(1, 0);
		// //backRightMotor.configPeakOutputReverse(-1, 0);
		// backRightMotor.config_kF(0, 0.05, 0);
		// backRightMotor.config_kP(0, 3, 0);
		// backRightMotor.config_kI(0, 0, 0);
		// backRightMotor.config_kD(0, 80, 0);
		// backRightMotor.setNeutralMode(NeutralMode.Brake);

		// frontRightMotor = new WPI_TalonFX(10);
		// //frontRightMotor.configFactoryDefault(0);
		// //frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		// //frontRightMotor.setSensorPhase(true);
		// frontRightMotor.setInverted(true);
		// //frontRightMotor.configNominalOutputForward(0, 0);
		// //frontRightMotor.configNominalOutputReverse(0, 0);
		// //frontRightMotor.configPeakOutputForward(1, 0);
		// //frontRightMotor.configPeakOutputReverse(-1, 0);
		// frontRightMotor.config_kF(0, 0.05, 0);
		// frontRightMotor.config_kP(0, 3, 0);
		// frontRightMotor.config_kI(0, 0, 0);
		// frontRightMotor.config_kD(0, 80, 0);
		// frontRightMotor.setNeutralMode(NeutralMode.Brake);

		shooter = new WPI_TalonFX(11);
		shooterSlave = new WPI_TalonFX(15);
		shooterSlave.follow(shooter);
		shooterSlave.setInverted(true);
		topBelt = new WPI_TalonSRX(12);//12
		topBelt.setInverted(true);
		topBelt.setNeutralMode(NeutralMode.Brake);
		indexWheel = new WPI_TalonSRX(13);//13
		indexWheel.setNeutralMode(NeutralMode.Brake);
		// bottomBelt = new WPI_TalonSRX(14);
		// bottomBelt.setInverted(true);
		
		colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

		blinkin = new Spark(1);

		navx = new AHRS(SPI.Port.kMXP);

	}

	public enum DelayToOff{
	
		/** the delay in seconds until the solenoids for certain ports turn off
		  *	below is just an example from 2019 
		  */

		elevator_stage1a(3.0),
		elevator_stage1b(3.0),
		elevator_stage2(3.0),
		elevator_floor(3.0),
		hatchPanelApparatus_collapsed(2.0),
		hatchIntake_hold(2.0),
		armExtend_extended(3.0),
		armLift_collapsed(2.0),
		armLift_mid(2.0),
		armLift_up(2.0);

		public double time;

		private DelayToOff(double time){

			this.time = time * 1000;

		}
	}

	public enum FunctionStateDelay{
	
		/** the delay in seconds between different states in the function switch statements
		  *	creates the wait time between cases in other words
		  *	below is just an example from 2019 (the examples should probably be removed 
		  *	when the next years copy of the code is made)
		  */

		intake_load(3.0);

		public double time;

		private FunctionStateDelay(double time){

			this.time = time * 1000;

		}
	}

	public enum PowerOutput{
	
		/** the power output in percentage for the different actions in the functions for the motors
		  * for example, an intake motor at a constant percentage of power while button pressed
		  *	below is just an example from 2019 (shooter was updated for RobotBasic)
		  */

		shooter_shooter_shoot(18500),
		shooter_shooter_stop(0.0),
		shooter_topBelt_run(1.0),
		shooter_topBelt_stop(0.0),
		shooter_indexWheel_run(0.5),
		shooter_indexWheel_stop(0.0),
		shooter_bottomBelt_run(1.0),
		shooter_bottomBelt_burp(-1.0),
		shooter_bottomBelt_stop(0.0);

		public double power;

		private PowerOutput(double power){

			this.power = power;

		}

	}
}

package org.usfirst.frc3534.RobotBasic.functions;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.RobotMap.PowerOutput;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.BottomBeltState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.IndexWheelState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.TopBeltState;

import edu.wpi.first.wpilibj.util.Color;

public class Shoot extends FunctionBase implements FunctionInterface{

    long originalTime = 0;
    double previous_shooter_velocity = 0;

    Color ball = ColorMatch.makeColor(0.331, 0.577, 0.091);
    Color noBall = ColorMatch.makeColor(0.267, 0.491, 0.241);

    int SHOOTER_SPEED_DROP = -50;

    ColorMatch colorMatcher;

    public Shoot(){

        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(ball);
        colorMatcher.addColorMatch(noBall);
        reset();
        completed();

    }

    @Override
    public void process(){

        if(!running && Buttons.Shoot.getButton()){

            this.reset();

        }

        switch(this.state) {

        case 0:

            if(Buttons.Shoot.getButton()){
                this.started();
                this.state = 10;
                System.out.println("Started");
            }

            break;

        case 10:
            
            Robot.shooter.setShooterState(ShooterState.SHOOT);
            if(RobotMap.shooter.getSelectedSensorVelocity() >= PowerOutput.shooter_shooter_shoot.power - 50){
                Robot.shooter.setTopBeltState(TopBeltState.RUN);
                Robot.shooter.setIndexWheelState(IndexWheelState.RUN);
                this.state = 40;
            }
            break;
            
            
        case 40:

            if(!Buttons.Shoot.getButton()) {

                Robot.shooter.setTopBeltState(TopBeltState.STOP);
                Robot.shooter.setIndexWheelState(IndexWheelState.STOP);
                this.state = 100;

            }

            break;/*

        case 20:

            if(Robot.shooter.getTopBeltState() == TopBeltState.STOP){
                Robot.shooter.setIndexWheelState(IndexWheelState.RUN);
                this.state = 30;
            }


            break;

        case 30:

            if(colorMatcher.matchClosestColor(RobotMap.colorSensor.getColor()).color == ball){
                Robot.shooter.setIndexWheelState(IndexWheelState.STOP);
                this.state = 40;
            }else if(!Buttons.Shoot.getButton()){

                Robot.shooter.setIndexWheelState(IndexWheelState.STOP);
                this.state = 100;

            }

            break;

        case 40:

            if(RobotMap.shooter.getSelectedSensorVelocity() >= PowerOutput.shooter_shooter_shoot.power - 50){
                Robot.shooter.setTopBeltState(TopBeltState.RUN);
                previous_shooter_velocity = PowerOutput.shooter_shooter_shoot.power;
                this.state = 50;
            }

            break;

        case 50:

            System.out.println("I am stuck here");
            System.out.println(RobotMap.shooter.getSelectedSensorVelocity() - previous_shooter_velocity);

            if(RobotMap.shooter.getSelectedSensorVelocity() - previous_shooter_velocity < SHOOTER_SPEED_DROP){
                Robot.shooter.setTopBeltState(TopBeltState.STOP);
                this.state = 100;
            }

            previous_shooter_velocity = RobotMap.shooter.getSelectedSensorVelocity();

            break;*/
        
        case 100:

            if(!Buttons.Shoot.getButton()) {

                Robot.shooter.setShooterState(ShooterState.STOP);

            }
            
            completed();

            break;
    
        }
    }
}
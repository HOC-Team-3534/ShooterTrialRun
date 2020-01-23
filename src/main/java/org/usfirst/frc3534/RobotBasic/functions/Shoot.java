package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;
import org.usfirst.frc3534.RobotBasic.OI.Buttons;
import org.usfirst.frc3534.RobotBasic.RobotMap.FunctionStateDelay;
import org.usfirst.frc3534.RobotBasic.RobotMap.PowerOutput;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.BottomBeltState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.IndexWheelState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.ShooterState;
import org.usfirst.frc3534.RobotBasic.systems.Shooter.TopBeltState;

public class Shoot extends FunctionBase implements FunctionInterface{

    long originalTime = 0;

    public Shoot(){

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
                
            }

            break;

        case 10:

            Robot.shooter.setShooterState(ShooterState.SHOOT);

            if(RobotMap.shooter.getSelectedSensorVelocity() >= PowerOutput.shooter_shooter_shoot.power){

                Robot.shooter.setTopBeltState(TopBeltState.RUN);
                originalTime = System.currentTimeMillis();
                this.state = 20;

            }
            
            break;

        case 20:

            Robot.shooter.setBottomBeltState(BottomBeltState.RUN);

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.shoot_load.time){

                originalTime = System.currentTimeMillis();
                this.state = 30;

            }

            break;

        case 30:

            Robot.shooter.setBottomBeltState(BottomBeltState.BURP);
            Robot.shooter.setIndexWheelState(IndexWheelState.RUN);

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.shoot_burp.time){

                originalTime = System.currentTimeMillis();
                this.state = 40;

            }

            break;

        case 40:

            Robot.shooter.setBottomBeltState(BottomBeltState.STOP);

            if(System.currentTimeMillis() - originalTime > FunctionStateDelay.shoot_index.time){

                originalTime = System.currentTimeMillis();
                this.state = 100;

            }

            break;

        case 100:

            Robot.shooter.setBottomBeltState(BottomBeltState.STOP);
            Robot.shooter.setIndexWheelState(IndexWheelState.STOP);
            if(!Buttons.Shoot.getButton()) {

                Robot.shooter.setShooterState(ShooterState.STOP);
                Robot.shooter.setTopBeltState(TopBeltState.STOP);

            }
            
            completed();

            break;
    
        }
    }
}
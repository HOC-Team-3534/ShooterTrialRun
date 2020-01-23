package org.usfirst.frc3534.RobotBasic.functions;

import org.usfirst.frc3534.RobotBasic.Robot;

public class FunctionProcessor{

    /**
     * Create a new variable of each of the functions
     */

    Shoot shoot;
    Intake intake;

    public FunctionProcessor(){

       /**
        * Instantiate each of the functions
        */

        shoot = new Shoot();
        intake = new Intake();


    }

    public void process(){

       /**
        * Call all of the process methods in each of the functions
        * Pay special attention to the order in which the function
        * methods are called
        */

        shoot.process();
        intake.process();

    }
}
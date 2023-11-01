package org.example;

import jade.core.Agent;

public class FunctionAgent extends Agent{
    protected void setup() {
        System.out.println(getLocalName() + " : Я успешно родился");

        this.addBehaviour(new CalcMyFunctionBehaviour());
        this.addBehaviour(new CatchInitiative());

        if (this.getLocalName().equals("FA1")) {
            this.addBehaviour(new StartBehaviour(this, 3000));
        }

    }
}

package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CalcMyFunctionBehaviour extends Behaviour {

    public void onStart() {

    }

    @Override
    public void action() {
        ACLMessage message_x = getAgent().receive(MessageTemplate.MatchConversationId("for_y_count"));
        if (message_x != null) {
            String content_x = message_x.getContent(); // Получение строки со значениями x, delta и ее разбиение
            String[] values = content_x.split(",");
            double x = Double.parseDouble(values[0]);
            double delta = Double.parseDouble(values[1]);

            double[] mas_y1 = new double[] {math.getResult1((float) (x-delta)), math.getResult2((float) (x-delta)), math.getResult3((float) (x-delta))};
            double[] mas_y = new double[] {math.getResult1((float) x), math.getResult2((float) x), math.getResult3((float) x)};
            double[] mas_y2 = new double[] {math.getResult1((float) (x+delta)), math.getResult2((float) (x+delta)), math.getResult3((float) (x+delta))};

            double y1 = 0;
            double y = 0;
            double y2 = 0;

            if (getAgent().getLocalName().equals("FA1")) { // Вычисление значений Y каждым агентом в зависимости от его функции
                y1 = mas_y1[0];
                y = mas_y[0];
                y2 = mas_y2[0];
            }
            else if (getAgent().getLocalName().equals("FA2")) {
                y1 = mas_y1[1];
                y = mas_y[1];
                y2 = mas_y2[1];
            }
            else if (getAgent().getLocalName().equals("FA3")) {
                y1 = mas_y1[1];
                y = mas_y[1];
                y2 = mas_y2[1];
            }
            ACLMessage message_y = new ACLMessage(ACLMessage.INFORM); // Ответное сообщение со значениями y1, y, y2
            message_y.setConversationId("counted_y");
            AID receiver = new AID(message_x.getSender().getLocalName(), false);
            message_y.addReceiver(receiver);
            String content_y = y1 + "," + y + "," + y2;
            message_y.setContent(content_y);
            getAgent().send(message_y);

        } else {
            block();
        }


    }

    @Override
    public boolean done() {
        return false; // поведение никогда не заканчивается
    }
}

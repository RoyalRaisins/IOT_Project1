package com.example.demo;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DeviceMqttCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost:" + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("receive: [topic]:" + topic + "  [msg]:" + message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("[isComplete]:" + token.isComplete() + "       " + token.getTopics());
    }
}
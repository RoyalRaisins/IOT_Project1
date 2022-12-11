package com.example.demo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;
public class MytMqttClient {
    MqttClient client;
    String Topic;
    public boolean SecureConnection(String tcpurl,String clientId, String userName, String passWord, String Topic){
        MemoryPersistence persistence = new MemoryPersistence();
        this.Topic = Topic;
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setCleanSession(true);
        options.setConnectionTimeout(0);

        try {
            client = new MqttClient(tcpurl, clientId, persistence);
            client.setCallback(new DeviceMqttCallback());
            client.connect(options);
            client.subscribe(Topic);
            System.out.println("Connection secured");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("SecureConnection: " +e.toString());
            return false;
        }
    }

    public void SendMessage(String msg){
        MqttTopic topic = client.getTopic(this.Topic);
        MqttMessage message = new MqttMessage(msg.getBytes(StandardCharsets.UTF_8));
        message.setRetained(true);
        try {
            topic.publish(message);
            System.out.println("Message Sent");
        } catch (Exception e) {
            System.out.println("SendMessage: " + e.getMessage());
        }
    }
}
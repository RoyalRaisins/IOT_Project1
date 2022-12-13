package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

import com.huawei.demo.device.QueryDeviceList;
import java.util.HashMap;
@RestController
@RequestMapping("/test")
public class TestController {
    JSONObject props = new JSONObject();
    HashMap<String, String> Status = new HashMap<String, String>();
    static boolean Manual = false;
    @RequestMapping("/h")
    public String test(){
        System.out.println("test server requested");
        return props.toString();
    }
    @PostMapping("/h")
    public String rec(@RequestBody String json){
        if(Status.isEmpty()){
            Status.put("Motor", "OFF");
            Status.put("light", "OFF");
        }
        //System.out.println(json);
        JSONObject msg = new JSONObject(json);
        JSONArray services = msg.getJSONObject("notify_data").getJSONObject("body").getJSONArray("services");
        props = services.getJSONObject(0).getJSONObject("properties");
        props.put("TimeStamp", msg.getString("event_time"));
        Status.put("Motor", props.getString("MotorStatus"));
        Status.put("light",props.getString("LightStatus"));
        //System.out.println("Properties: \n"+props);
        if(!Manual){
            if(Status.get("Motor").toString().equals("OFF") && props.getInt("Temperature") >= 30)ConfigDevice("Motor","ON");  //降温
            else if(Status.get("Motor").toString().equals("ON") && props.getInt("Temperature") <= 27)ConfigDevice("Motor","OFF");
            if(Status.get("light").toString().equals("OFF") && props.getInt("Humidity") >= 40)ConfigDevice("light","ON");  //灭虫
            else if(Status.get("light").toString().equals("ON") && props.getInt("Humidity") <= 37)ConfigDevice("light","OFF");
        }
        return "Post successful";
    }

    @PostMapping("/command")
    public String RecCommand(@RequestBody String json){
        System.out.println("Body: " + json);
        JSONObject msg = new JSONObject(json);
        try {
            if(ConfigDevice(msg.getString("Device"), msg.getString("Status")))return "success";
            else return "An error occurred";
        } catch (Exception e) {
            e.getStackTrace();
            return "An error occurred";
            // TODO: handle exception
        }
        
    }
    @PostMapping("/manual/on")
    public String EnableManual(){
        System.out.println("Switching to manual");
        Manual = true;
        return "Manual control enabled";
    }
    @PostMapping("/manual/off")
    public String DisableManual(){
        System.out.println("Switching to auto");
        Manual = false;
        return "Manual control disabled";
    }
    @RequestMapping("/manual")
    public String isManual(){
        return Manual? "YES":"NO";
    }
    public Boolean ConfigDevice(String function, String status){
            System.out.println("Configuring "+ function+ " to be "+ status);
            JSONObject params = new JSONObject();
            params.put(function, status);
            JSONObject command = new JSONObject();
            command.put("service_id","Agriculture");
            command.put("command_name", "Agriculture_Control_"+ function);
            command.put("paras",params);
            try {
                new QueryDeviceList().SendCommand(command);
                Status.put(function, status);
                return true;
            } catch (Exception e) {
                System.out.println("Configuration failed");
                e.getStackTrace();
                return false;
            }
    }


}

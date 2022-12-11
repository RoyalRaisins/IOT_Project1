package com.huawei.demo.product;

import com.huawei.demo.apig.SignUtil;
import com.huawei.pojo.product.*;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.JsonUtils;
import com.huawei.util.StreamClosedHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProductByAK {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        AddProduct addProduct = new AddProduct();
        Commands commands = new Commands();
        CommandParam commandParam = new CommandParam();
        Properties properties = new Properties();
        Response response = new Response();
        ResponseParam responseParam = new ResponseParam();
        ServiceCapability serviceCapability = new ServiceCapability();

        addProduct.setName("Thermometer001");
        addProduct.setDevice_type("Thermometer");
        addProduct.setProtocol_type("HTTPS");
        addProduct.setData_format("binary");
        addProduct.setManufacturer_name("ABC");
        addProduct.setIndustry("smartCity");
        addProduct.setDescription("this is a thermometer product by Huawei.");

        List<ServiceCapability> list = new ArrayList<ServiceCapability>();
        serviceCapability.setService_id("temperature");
        serviceCapability.setService_type("temperature");
        serviceCapability.setDescription("temperature");
        serviceCapability.setDescription("temperature");
        serviceCapability.setOption("Mandatory");

        List<Properties> propertiesList = new ArrayList<Properties>();
        properties.setProperty_name("temperature");
        properties.setRequired(true);
        properties.setData_type("decimal");
        properties.setMax(100);
        properties.setMin(1);
        properties.setMax_length(100);
        properties.setStep(0.1);
        properties.setUnit("centigrade");
        properties.setMethod("R");
        properties.setDescription("force");

        propertiesList.add(properties);
        serviceCapability.setProperties(propertiesList);

        List<Commands> commandsList = new ArrayList<Commands>();
        commands.setCommand_name("reboot");

        List<CommandParam> commandParamList = new ArrayList<CommandParam>();
        commandParam.setPara_name("force");
        commandParam.setRequired(true);
        commandParam.setData_type("string");
        commandParam.setMax(100);
        commandParam.setMin(1);
        commandParam.setMax_length(100);
        commandParam.setStep(0.1);
        commandParam.setUnit("km/h");
        commandParam.setDescription("force");

        commandParamList.add(commandParam);
        commands.setParas(commandParamList);

        List<Response> responseList = new ArrayList<Response>();
        response.setResponse_name("ACK");

        List<ResponseParam> responseParamList = new ArrayList<ResponseParam>();
        responseParam.setPara_name("force");
        responseParam.setRequired(true);
        responseParam.setData_type("string");
        responseParam.setMax(100);
        responseParam.setMin(1);
        responseParam.setMax_length(100);
        responseParam.setStep(0.1);
        responseParam.setUnit("km/h");
        responseParam.setDescription("force");

        responseParamList.add(responseParam);

        response.setParas(responseParamList);
        responseList.add(response);

        commands.setResponses(responseList);
        commandsList.add(commands);
        serviceCapability.setCommands(commandsList);
        list.add(serviceCapability);
        addProduct.setService_capabilities(list);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();

        String url = Constants.PRODUCT_COMMAND_URL;
        String project_id = "23123";
        url = String.format(url, project_id);

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");

        HttpRequestBase httpRequestBase = SignUtil.signRequest(url, "POST", header, JsonUtils.Obj2String(addProduct), null);

        StreamClosedHttpResponse httpResponse = (StreamClosedHttpResponse)httpUtils.execute(httpRequestBase);

        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());
    }
}

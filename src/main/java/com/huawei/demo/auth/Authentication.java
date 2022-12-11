package com.huawei.demo.auth;

import com.huawei.pojo.auth.*;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.JsonUtils;
import com.huawei.util.StreamClosedHttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.Header;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authentication {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        System.out.println(getToken());
    }

    public static String getToken() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        File file = new File("token.text");
        String token = "";
        String str = "";
        String expiresTime = "";
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file));
                bufferedReader = new BufferedReader(inputStreamReader);
                if ((str = bufferedReader.readLine()) != null) {
                    token = str;
                }
                if ((str = bufferedReader.readLine()) != null) {
                    expiresTime = str;
                }
            } catch (IOException e) {
                System.out.println("token exist.");
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("close stream faild");
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    System.out.println("close stream faild");
                }
            }
        }

        try {
            if (expiresTime.trim().length() > 0) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date expiresDate = dateFormat.parse(expiresTime);
                Date now = new Date(System.currentTimeMillis());
                if (now.before(expiresDate) && token.trim().length() > 0) {
                    return token;
                }
            }
        } catch (ParseException e) {
            System.out.println("parse token expires time failed");
        }

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        AuthDTO authDTO = new AuthDTO();
        DomainDTO domainDTO = new DomainDTO();
        IdentityDTO identityDTO = new IdentityDTO();
        PasswordDTO passwordDTO = new PasswordDTO();
        UserDTO userDTO = new UserDTO();
        ProjectDTO projectDTO = new ProjectDTO();
        ScopeDTO scopeDTO = new ScopeDTO();
        projectDTO.setName("cn-north-4");
        scopeDTO.setProject(projectDTO);
        domainDTO.setName("binbin1181130");

        userDTO.setName("trytry");
        userDTO.setPassword("cyy991108!");
        userDTO.setDomain(domainDTO);

        passwordDTO.setUser(userDTO);
        List<String> method = new ArrayList<String>();
        method.add("password");
        identityDTO.setMethods(method);
        identityDTO.setPassword(passwordDTO);

        authDTO.setIdentity(identityDTO);
        authDTO.setScope(scopeDTO);

        accessTokenDTO.setAuth(authDTO);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        StreamClosedHttpResponse httpResponse = null;
        try {
            httpResponse = httpUtils.doPost(Constants.TOKEN_ACCESS_URL, header, JsonUtils.Obj2String(accessTokenDTO));
        } catch (Exception exception) {
            System.err.println("please check your network.");
            return null;
        }

        Header[] headers = httpResponse.getHeaders("X-Subject-Token");
        token = headers[0].getValue();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(httpResponse.getContent());
        String rawExpiresAt = jsonNode.findValue("token").findValuesAsText("expires_at").get(0);
        String expiresAt = rawExpiresAt.split("\\.")[0];

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(token.getBytes());
        fileOutputStream.write('\n');
        fileOutputStream.write(expiresAt.getBytes());
        fileOutputStream.close();
        return token;
    }
}

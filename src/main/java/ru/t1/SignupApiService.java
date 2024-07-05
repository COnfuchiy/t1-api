package ru.t1;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import ru.t1.enums.ApiEndpoints;
import ru.t1.request.SetStatusRequest;
import ru.t1.request.SignupRequest;
import ru.t1.response.*;
import ru.t1.utils.Base64Converter;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.exit;

public class SignupApiService extends BaseApiService {

    private final String defaultConfigPath = "config.properties";

    private final String configPath;
    private final String lastName;
    private final String firstName;
    private final String role;
    private final String email;

    private final Configuration config;

    private String apiHost;
    private final HashMap<ApiEndpoints, String> endpointsUrls= new HashMap<>();

    public SignupApiService(String configPath, String lastName, String firstName, String role, String email) throws Exception {
        this.lastName = lastName;
        this.firstName = firstName;
        this.configPath = configPath;
        this.role = role;
        this.email = email;
        config = setupConfig(configPath);
        setupApiUrls();
    }

    public void init() throws Exception {
        var rolesResponse = sendGetRolesRequest();
        System.out.println("Response: " + rolesResponse.getRawResponse());

        if (!Objects.equals(role, "") && !rolesResponse.getResponse().roles.contains(role)){
            throw new Exception("Role " + role + " doesn't in roles list: " + rolesResponse.getResponse().roles);
        }

        var signupResponse = sendSignupRequest();
        System.out.println("Response: " + signupResponse.getRawResponse());

        var getCodeResponse = sendGetCodeRequest();
        System.out.println("Response: " + getCodeResponse.getRawResponse());


        var token = Base64Converter.encode(email, getCodeResponse.getResponse().getCode());
        var setStatusResponse = sendSetStatusRequest(token, "increased");
        System.out.println("Response: " + setStatusResponse.getRawResponse());
    }

    public void setupApiUrls() throws Exception {
        apiHost = config.getString("API_HOST", "");
        if (Objects.equals(apiHost, "")) {
            throw new Exception("API_HOST is empty in " + configPath);
        }

        var apiHostAsUrlObject = URI.create(apiHost);

        endpointsUrls.put(ApiEndpoints.GET_ROLES, String.valueOf(apiHostAsUrlObject.resolve(config.getString("API_GET_ROLES", ""))));
        endpointsUrls.put(ApiEndpoints.SIGNUP, String.valueOf(apiHostAsUrlObject.resolve(config.getString("API_SIGNUP", ""))));
        endpointsUrls.put(ApiEndpoints.GET_CODE, String.valueOf(apiHostAsUrlObject.resolve(config.getString("API_GET_CODE", ""))));
        endpointsUrls.put(ApiEndpoints.SET_STATUS, String.valueOf(apiHostAsUrlObject.resolve(config.getString("API_SET_STATUS", ""))));
        for (Map.Entry<ApiEndpoints, String> param : endpointsUrls.entrySet()) {
            if (Objects.equals(param.getValue(), "")) {
                throw new Exception(param.getKey().name() + " is empty in " + configPath);
            }
        }
    }

    private Configuration setupConfig(String configPath) {
        try {
            return new Configurations().properties((new File(!Objects.equals(configPath, "") ? configPath : defaultConfigPath)));
        } catch (Exception e) {
            System.err.println("Failed to load configuration file: " + e.getMessage());
            exit(1);
        }
        return null;
    }

    public ResponseEntity<GetRolesResponse> sendGetRolesRequest() throws Exception {
        var response = sendGetRequest(endpointsUrls.get(ApiEndpoints.GET_ROLES), null);
        try {
            return new ResponseEntity<>(response, GetRolesResponse.decode(response));
        } catch (Exception e) {
            throw new Exception("Failed to get roles: " + e.getMessage());
        }
    }

    public ResponseEntity<SignupResponse> sendSignupRequest() throws Exception {
        var signupRequest = new SignupRequest(lastName, firstName, email, role);
        var jsonBody = signupRequest.encode();
        System.out.println("Signup request body: " + jsonBody);

        var response = sendPostRequest(endpointsUrls.get(ApiEndpoints.SIGNUP), null, jsonBody);

        try {
            return new ResponseEntity<>(response, new SignupResponse(response));
        } catch (Exception e) {
            throw new Exception("Failed to signup: " + e.getMessage());
        }
    }

    private ResponseEntity<GetCodeResponse> sendGetCodeRequest() throws Exception {
        Map<String, String> queryParams = Map.of("email", email);
        var response = sendGetRequest(endpointsUrls.get(ApiEndpoints.GET_CODE), queryParams);
        try {
            return new ResponseEntity<>(response, new GetCodeResponse(response));
        } catch (Exception e) {
            throw new Exception("Failed to get code: " + e.getMessage());
        }
    }

    private ResponseEntity<SetStatusResponse> sendSetStatusRequest(String token, String status) throws Exception {
        var setStatusRequest = new SetStatusRequest(token, status);
        var jsonBody = setStatusRequest.encode();
        System.out.println("SetStatus request body: " + jsonBody);

        var response = sendPostRequest(endpointsUrls.get(ApiEndpoints.SET_STATUS), null, jsonBody);

        try {
            return new ResponseEntity<>(response, new SetStatusResponse(response));
        } catch (Exception e) {
            throw new Exception("Failed to set status: " + e.getMessage());
        }
    }
}

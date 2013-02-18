package com.openrest.v1_1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAppCredentialsRequest extends Request {
    public static final String TYPE = "get_app_credentials";
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public GetAppCredentialsRequest() {}
    
    public GetAppCredentialsRequest(String accessToken, AppId appId) {
    	this.accessToken = accessToken;
    	this.appId = appId;
    }
    
    @JsonInclude(Include.NON_NULL)
    public String accessToken;
    
    @JsonInclude(Include.NON_NULL)
    public AppId appId;
}

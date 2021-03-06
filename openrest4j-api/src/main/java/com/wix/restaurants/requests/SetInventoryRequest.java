package com.wix.restaurants.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.openrest.v1_1.Inventory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SetInventoryRequest extends Request {
	public static final String TYPE = "set_inventory";
    private static final long serialVersionUID = 1L;
    
    /** Default constructor for JSON deserialization. */
    public SetInventoryRequest() {}
    
    public SetInventoryRequest(String accessToken, String organizationId, String locationId, Inventory inventory) {
    	this.accessToken = accessToken;
    	this.organizationId = organizationId;
    	this.locationId = locationId;
    	this.inventory = inventory;
    }
    
    @JsonInclude(Include.NON_NULL)
    public String accessToken;
    
    @JsonInclude(Include.NON_NULL)
    public String organizationId;

    @JsonInclude(Include.NON_NULL)
    public String locationId;
    
    @JsonInclude(Include.NON_NULL)
    public Inventory inventory;
}

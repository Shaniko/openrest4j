package com.openrest.v1_1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    /** Delivery to an address of your choice. */
    public static final String DELIVERY_TYPE_DELIVERY = "delivery";
    /** Take-out from the restaurant. */
    public static final String DELIVERY_TYPE_TAKEOUT = "takeout";

    /** All known delivery methods. */
    public static final Set<String> ALL_DELIVERY_TYPES = new HashSet<String>(Arrays.asList(new String[] {
        DELIVERY_TYPE_DELIVERY, DELIVERY_TYPE_TAKEOUT
    }));

    public Delivery(String type, Address address, Long time, Integer charge) {
        this.type = type;
        this.address = address;
        this.time = time;
        this.charge = charge;
    }

    /** Default constructor for JSON deserialization. */
    public Delivery() {}
    
    @Override
	public Object clone() {
    	return new Delivery(type, ((address != null) ? (Address) address.clone() : null), time, charge);
	}

    /** Delivery type. */
    @JsonInclude(Include.NON_NULL)
    public String type;

    /** Address to deliver to (valid only if type is DELIVERY_TYPE_DELIVERY) */
    @JsonInclude(Include.NON_NULL)
    public Address address;
    
    /** Timestamp by which the order will be delivered or ready for pick-up. */
    @JsonInclude(Include.NON_NULL)
    public Long time;
    
    public java.util.Date time() {
        return ((time != null) ? new java.util.Date(time) : null);
    }
    
    /** The delivery charge (in "cents"). */
    @JsonInclude(Include.NON_DEFAULT)
    public Integer charge = 0;
}

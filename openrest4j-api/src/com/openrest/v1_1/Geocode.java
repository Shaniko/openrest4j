package com.openrest.v1_1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geocode implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
	public Geocode(String countryCode, String region, String city, LatLng latLng) {
		this.countryCode = countryCode;
		this.region = region;
		this.city = city;
		this.latLng = latLng;
    }

    /** Default constructor for JSON deserialization. */
    public Geocode() {}
    
    @Override
	public Object clone() {
    	return new Geocode(countryCode, region, city,
    			((latLng != null) ? (LatLng) latLng.clone() : null));
	}

    @JsonInclude(Include.NON_NULL)
    public String countryCode;

    @JsonInclude(Include.NON_NULL)
    public String region;

    @JsonInclude(Include.NON_NULL)
    public String city;
    
    @JsonInclude(Include.NON_NULL)
    public LatLng latLng;
}

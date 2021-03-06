package com.openrest.olo.dispatches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.openrest.v1_1.Address;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Delivery to an address of your choice. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDispatch extends Dispatch {
    public static final String TYPE = "delivery";
    private static final long serialVersionUID = 1L;

    /** Default constructor for JSON deserialization. */
    public DeliveryDispatch() {}

    public DeliveryDispatch(Address address, Date time, String timeGuarantee, Integer charge, Map<String, String> properties) {
        super(time, timeGuarantee, charge, properties);

        this.address = address;
    }

    @Override
    public DeliveryDispatch clone() {
        return cloneImpl();
    }

    @Override
    protected DeliveryDispatch cloneImpl() {
        return new DeliveryDispatch(
                ((address != null) ? address.clone() : null),
                (time != null) ? (Date) time.clone() : null,
                timeGuarantee, charge,
                ((properties != null) ? new LinkedHashMap<>(properties) : null));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeliveryDispatch that = (DeliveryDispatch) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    /** Address to deliver to. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Address address;
}

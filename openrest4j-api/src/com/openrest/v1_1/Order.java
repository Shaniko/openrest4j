package com.openrest.v1_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    /**
     * The order has been submitted by the user, and awaits her final approval.
     * The restaurant is not made of the order.
     */
    public static final String ORDER_STATUS_SUBMITTED = "submitted";
    /**
     * The restaurant required further confirmation before the order is submitted
     * (e.g. validating the user's phone number by SMS).
     */
    public static final String ORDER_STATUS_PENDING = "pending";
    /** The order has been approved by the user, and awaits processing by the restaurant. */
    public static final String ORDER_STATUS_NEW = "new";
    /** The order has been processed and accepted by the restaurant. */
    public static final String ORDER_STATUS_ACCEPTED = "accepted";
    /** The order has been canceled. */
    public static final String ORDER_STATUS_CANCELLED = "canceled";
    
    /** All known order statuses. */
    public static final Set<String> ALL_ORDER_STATUSES = new HashSet<String>(Arrays.asList(new String[] {
    		ORDER_STATUS_SUBMITTED, ORDER_STATUS_PENDING, ORDER_STATUS_NEW, ORDER_STATUS_ACCEPTED, ORDER_STATUS_CANCELLED
    }));

    /** Constructs a previously submitted order from persisted data. */
    public Order(String id, Map<String, String> externalIds, String restaurantId, String locale, List<OrderItem> orderItems,
    		String comment, Integer price, Delivery delivery, Contact contact, List<Payment> payments,
            Integer takeoutPacks, List<Charge> charges, java.util.Date created, java.util.Date received, java.util.Date modified,
            User user, ClubMember clubMember, String status, String shareToken,
            String affiliate, String ref, Boolean legacyHierarchy, Map<String, String> properties, List<LogEntry> log) {

        this.id = id;
        this.externalIds = externalIds;
        this.restaurantId = restaurantId;
        this.locale = locale;
        this.orderItems = orderItems;
        this.comment = comment;
        this.price = price;
        this.delivery = delivery;
        this.contact = contact;
        this.payments = payments;
        this.takeoutPacks = takeoutPacks;
        this.charges = charges;
        this.created = ((created != null) ? created.getTime() : null);
        this.received = ((received != null) ? received.getTime() : null);
        this.modified = ((modified != null) ? modified.getTime() : null);
        this.user = user;
        this.clubMember = clubMember;
        this.status = status;
        this.shareToken = shareToken;
        this.affiliate = affiliate;
        this.ref = ref;
        this.legacyHierarchy = legacyHierarchy;
        this.properties = properties;
        this.log = log;
    }

    /** Constructs a new order to be submitted. */
    public Order(String locale, List<OrderItem> orderItems, String comment, Integer price,
            Delivery delivery, Contact contact, List<Payment> payments,
            Integer takeoutPacks, List<Charge> charges, ClubMember clubMember, String affiliate, String ref,
            Boolean legacyHierarchy, Map<String, String> properties) {
        this(null, new HashMap<String, String>(), null, locale, orderItems, comment, price, delivery, contact, payments,
        		takeoutPacks, charges, null, null, null, null, clubMember, null, null,
        		affiliate, ref, legacyHierarchy, properties, Collections.<LogEntry>emptyList());
    }

    /** Default constructor for JSON deserialization. */
    public Order() {}
    
    @Override
	public Object clone() {
    	final List<OrderItem> clonedOrderItems;
    	if (orderItems != null) {
    		clonedOrderItems = new ArrayList<OrderItem>(orderItems.size());
    		for (OrderItem orderItem : orderItems) {
    			clonedOrderItems.add((OrderItem) orderItem.clone());
    		}
    	} else {
    		clonedOrderItems = null;
    	}
    	
    	final List<Payment> clonedPayments;
    	if (payments != null) {
    		clonedPayments = new ArrayList<Payment>(payments.size());
    		for (Payment payment : payments) {
    			clonedPayments.add((Payment) payment.clone());
    		}
    	} else {
    		clonedPayments = null;
    	}
    	
    	final List<Charge> clonedCharges;
    	if (charges != null) {
    		clonedCharges = new ArrayList<Charge>(charges.size());
    		for (Charge charge : charges) {
    			clonedCharges.add((Charge) charge.clone());
    		}
    	} else {
    		clonedCharges = null;
    	}
    	
    	final List<LogEntry> clonedLog;
    	if (log != null) {
    		clonedLog = new ArrayList<LogEntry>(log.size());
    		for (LogEntry logEntry : log) {
    			clonedLog.add((LogEntry) logEntry.clone());
    		}
    	} else {
    		clonedLog = null;
    	}
    	
    	return new Order(id,
    			((externalIds != null) ? new HashMap<String, String>(externalIds) : null),
    			restaurantId, locale, clonedOrderItems, comment, price,
    			((delivery != null) ? (Delivery) delivery.clone() : null),
    			((contact != null) ? (Contact) contact.clone() : null),
    			clonedPayments, takeoutPacks, clonedCharges, created(), received(), modified(),
    			((user != null) ? (User) user.clone() : null),
    			((clubMember != null) ? (ClubMember) clubMember.clone() : null),
    			status, shareToken, affiliate, ref, legacyHierarchy,
    			((properties != null) ? new HashMap<String, String>(properties) : null),
    			clonedLog);
	}
    
    public java.util.Date created() {
        return ((created != null) ? new java.util.Date(created.longValue()) : null);
    }
    
    public java.util.Date received() {
        return ((received != null) ? new java.util.Date(received.longValue()) : null);
    }

    public java.util.Date modified() {
        return ((modified != null) ? new java.util.Date(modified.longValue()) : null);
    }

    /** The order's unique id. */
    @JsonInclude(Include.NON_NULL)
    public String id;
    
    @JsonInclude(Include.NON_DEFAULT)
    public Map<String, String> externalIds = new HashMap<String, String>();

    /** The restaurant's unique id. */
    @JsonInclude(Include.NON_NULL)
    public String restaurantId;
    
    /** The order's locale. */
    @JsonInclude(Include.NON_NULL)
    public String locale;

    /** The ordered items. */
    @JsonInclude(Include.NON_DEFAULT)
    public List<OrderItem> orderItems = new ArrayList<OrderItem>();

    /** Comment to the restaurant (as opposed to the delivery person!). */
    @JsonInclude(Include.NON_NULL)
    public String comment;

    /** Total price of the order. */
    @JsonInclude(Include.NON_NULL)
    public Integer price;

    /* Delivery method. */
    @JsonInclude(Include.NON_NULL)
    public Delivery delivery;

    /* Contact details. */
    @JsonInclude(Include.NON_NULL)
    public Contact contact;

    /* Payments. */
    @JsonInclude(Include.NON_DEFAULT)
    public List<Payment> payments = new ArrayList<Payment>();

    /**
     * Number of "takeout packs" (e.g. cutlery and condiments) to deliver with the order.
     * For environmental reasons, clients should be encouraged to set this to 0.
     */
    @JsonInclude(Include.NON_NULL)
    public Integer takeoutPacks;
    
    /**
	 * Extra charges or discounts associated with the order, ordered by priority
	 * in descending order.
	 */
    @JsonInclude(Include.NON_DEFAULT)
    public List<Charge> charges = new ArrayList<Charge>();

    /** The order's creation timestamp. */
    @JsonInclude(Include.NON_NULL)
    public Long created;
    
    /**
     * Timestamp in which the order was marked as "new" in the syste. This may differ from the
     * order's creation timestamp for orders that were pending upon creation.
     */
    @JsonInclude(Include.NON_NULL)
    public Long received;

    /** The order's last modification timestamp. */
    @JsonInclude(Include.NON_NULL)
    public Long modified;

    /** The ordering user. */
    @JsonInclude(Include.NON_NULL)
    public User user;
    
    /** The ordering club member. */
    @JsonInclude(Include.NON_NULL)
    public ClubMember clubMember;

    /** The order's status. */
    @JsonInclude(Include.NON_NULL)
    public String status;

    /** The order's share-token. */
    @JsonInclude(Include.NON_NULL)
    public String shareToken;
    
    /** Affiliate-id, for orders that came through affiliate marketing. */
    @JsonInclude(Include.NON_NULL)
    public String affiliate;
    
    /**
     * Affiliate-specific referrer-id for performance tracking, e.g. 
     * Facebook campaign id, iPhone application id, self-service station id.
     */
    @JsonInclude(Include.NON_NULL)
    public String ref;
    
    /**
     * Whether or not the order was submitted and should be displayed with a
     * legacy "2-level hierarchy".
     */
    @JsonInclude(Include.NON_DEFAULT)
    public Boolean legacyHierarchy = Boolean.FALSE;
    
    /**
     * Map of user-defined extended properties. Developers should use unique
     * keys, e.g. "com.googlecode.openrestext".
     */
    @JsonInclude(Include.NON_DEFAULT)
    public Map<String, String> properties = new HashMap<String, String>();
    
    /** Change log for this order. */
    @JsonInclude(Include.NON_DEFAULT)
    public List<LogEntry> log = new ArrayList<LogEntry>();
    
    /** The order in HTML format. */
    @JsonInclude(Include.NON_NULL)
    public String html;
}

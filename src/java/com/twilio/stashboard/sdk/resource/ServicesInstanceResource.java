/**
 * 
 */
package com.twilio.stashboard.sdk.resource;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.stashboard.sdk.TwilioServiceStatusReadRestClient;

// TODO: Auto-generated Javadoc
/**
 * The Class InstanceResource.
 */
public abstract class ServicesInstanceResource extends ServicesResource {
	
	/** The properties. */
	private Map<String, Object> properties;

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 */
	public ServicesInstanceResource(TwilioServiceStatusReadRestClient client) {
		super(client);
		this.properties = new HashMap<String, Object>();
	}

	/**
	 * Instantiates a new instance resource.
	 *
	 * @param client the client
	 * @param properties the properties
	 */
	public ServicesInstanceResource(TwilioServiceStatusReadRestClient client,
			Map<String, Object> properties) {
		super(client);
		this.properties = new HashMap<String, Object>(properties);
		this.setLoaded(true);
	}

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return the property
	 */
	public String getProperty(String name) {
		Object prop = properties.get(name);

		if (prop == null && !this.isLoaded()) {
			try {
				this.load(new HashMap<String, String>());
			} catch (TwilioRestException e) {
				// TODO add log support
				throw new RuntimeException(e);
			}
		}

		prop = properties.get(name);

		if (prop == null) {
			throw new IllegalArgumentException("Property " + name
					+ " does not exist");
		}

		if (prop instanceof String) {
			return (String) prop;
		}

		throw new IllegalArgumentException("Property " + name
				+ " is an object.  Use getOjbect() instead.");
	}

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return the property
	 */
	public Object getPropertyObject(String name) {
		Object prop = properties.get(name);

		if (prop == null && !this.isLoaded()) {
			try {
				this.load(new HashMap<String, String>());
			} catch (TwilioRestException e) {
				// TODO add log support
				throw new RuntimeException(e);
			}
		}

		prop = properties.get(name);

		if (prop == null) {
			throw new IllegalArgumentException("Property " + name
					+ " does not exist");
		}

		return prop;
	}

	
	/**
	 * Sets the property.
	 *
	 * @param name the name
	 * @param value the value
	 */
	protected void setProperty(String name, String value) {
		properties.put(name, value);
	}
	
	/**
	 * Update.
	 *
	 * @param params the params
	 * @throws TwilioRestException the twilio rest exception
	 */
	public void update(Map<String, String> params) throws TwilioRestException {
		this.getClient().safeRequest(this.getResourceLocation(), "POST", params);
	}
	
	/* (non-Javadoc)
	 * @see com.twilio.sdk.resource.Resource#parseResponse(com.twilio.sdk.TwilioRestResponse)
	 */
	@Override
	protected void parseResponse(TwilioRestResponse response) {
		Map<String, Object> properties = response.toMap();
		this.properties = new HashMap<String, Object>(properties);
	}
}

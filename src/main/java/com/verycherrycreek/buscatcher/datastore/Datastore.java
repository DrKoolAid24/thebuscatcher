/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.datastore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.verycherrycreek.buscatcher.datastore.DatastoreProperties.DATASTORE_TECHNOLOGY;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public abstract class Datastore {

	// Key that identifies the type of TransitAuthority
	public DATASTORE_TECHNOLOGY datastoreTechnology;
	protected String resourceName;
	protected Properties props;
	
	/**
	 * @param datastoreTechnology Identifies the storage technology used to save the data to
	 * @param resourceName Name of resource (properties file) that will be used to configure the Datastore
	 * @param props Properties file that contains the configuration values of the Datastore
	 */
	public Datastore(DATASTORE_TECHNOLOGY pDatastoreTechnology, String pResourceName) {
		this.datastoreTechnology = pDatastoreTechnology;
		this.resourceName = pResourceName;
	}

	protected boolean getConfiguration() {
		boolean retVal = false;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
			retVal = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	
}

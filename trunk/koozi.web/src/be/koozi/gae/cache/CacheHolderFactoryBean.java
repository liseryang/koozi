package be.koozi.gae.cache;

import java.util.Collections;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.springframework.beans.factory.FactoryBean;

/**
 * Simple wrapper to expose a Cache in the Spring context.
 */
@SuppressWarnings("unchecked")
public class CacheHolderFactoryBean implements FactoryBean {

	Map cacheProperties = Collections.emptyMap();

	public void setCacheProperties(Map cacheProperties) {
		this.cacheProperties = cacheProperties;
	}

	public Object getObject() throws Exception {
		Cache cache = CacheManager.getInstance().getCacheFactory().createCache(cacheProperties);
		return new CacheHolder(cache);
	}

	public Class<CacheHolder> getObjectType() {
		return CacheHolder.class;
	}

	public boolean isSingleton() {
		return false;
	}

}

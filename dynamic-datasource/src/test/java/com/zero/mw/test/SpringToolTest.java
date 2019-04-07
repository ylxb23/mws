package com.zero.mw.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.zero.mw.dynamic.mybatis.DsDruidConfiguration;
import com.zero.mw.dynamic.mybatis.DynamicConstants;
import com.zero.mw.dynamic.mybatis.DynamicPropertyUtil;

/**
 * 
 * @date 2019年3月30日 下午10:13:04
 * @author zero
 */
public class SpringToolTest extends TestBase implements EnvironmentAware {
	
	private Environment env;
	
	@Test
	public void testParseDsConnectionConfig() {
		DsDruidConfiguration conf = DynamicPropertyUtil.parseDsConnectionConfiguration(this.env, "_default");
		logger.debug("Parse ds connection configuration, dsName:{}, result:{}", "_default", conf);
		Assert.assertEquals("configuration properties not getted.", "default", conf.getName());
	}
	
	@Test
	@SuppressWarnings({ "unchecked" })
	public void testRelaxedPropertyResolver() {
		Map<String, Map<String, Set<String>>> dsInfoMap = new HashMap<>();
		Binder binder = Binder.get(env);
		Map<String, Object> pairs = binder.bind(DynamicConstants.DS_MYSQL, Bindable.mapOf(String.class, Object.class)).get();
		for(Entry<String, Object> entry : pairs.entrySet()) {
			String db = entry.getKey();
			Map<String, String> obj = (Map<String, String>) entry.getValue();
			dsInfoMap.putIfAbsent(db, new HashMap<>());
			String rurl = obj.get(DynamicConstants.READ_URL);
			String wurl = obj.get(DynamicConstants.WRITE_URL);
			if(rurl != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.READ, new HashSet<>());
				dsInfoMap.get(db).get(DynamicConstants.READ).add(rurl);
			}
			if(wurl != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.WRITE, new HashSet<>());
				dsInfoMap.get(db).get(DynamicConstants.WRITE).add(wurl);
			}
			String url = obj.get(DynamicConstants.DEFAULT_URL);
			if(url != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.DEFAULT, new HashSet<>());
				dsInfoMap.get(db).get(DynamicConstants.DEFAULT).add(url);
			}
			if(rurl != null && wurl != null && url != null) {
				logger.warn("Duplicate configuration dataSource urls configuration, dsName:{}", db);
			}
			if(rurl == null && wurl == null && url == null) {
				logger.info("DataSource urls configuration without url configurated, dsName:{}", db);
			}
		}
		logger.debug("mysql env pairs: {} \n dsInfos: {}", pairs, dsInfoMap);
	}


	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
}

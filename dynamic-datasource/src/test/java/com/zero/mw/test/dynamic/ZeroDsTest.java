package com.zero.mw.test.dynamic;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSON;
import com.zero.mw.test.TestBase;
import com.zero.mw.test.dal.zero.entity.AppInfo;
import com.zero.mw.test.dal.zero.entity.AppInfoExample;
import com.zero.mw.test.dal.zero.mapper.ZeroAppInfoMapper;

/**
 * 
 * @date 2019年4月7日 下午9:51:28
 * @author zero
 */
public class ZeroDsTest extends TestBase {
	@Autowired
	private ZeroAppInfoMapper zeroAppInfoMapper;
	
	
	@Test
	public void testRead() {
		AppInfoExample e = new AppInfoExample();
		AppInfoExample.Criteria c = e.createCriteria();
		c.andAppVersionEqualTo("2.0");
		List<AppInfo> list = zeroAppInfoMapper.selectByExample(e);
		logger.debug("read result: {}", JSON.toJSONString(list));
	}
	
	@Rollback
	@Test
	public void testWrite() {
		AppInfo info = new AppInfo();
		info.setAppName("This is app name");
		info.setAppVersion("1.0");
		info.setCreateTime(new Date());
		info.setDescription("Inserted by java");
		info.setUpdateTime(new Date());
		int result = zeroAppInfoMapper.insertSelective(info);
		Assert.assertTrue(result > 0);
	}
}

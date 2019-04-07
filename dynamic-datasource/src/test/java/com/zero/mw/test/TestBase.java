package com.zero.mw.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @date 2019年3月30日 下午10:08:48
 * @author zero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {TestMain.class})
public abstract class TestBase {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
}

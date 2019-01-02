package com.snnu.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.snnu.o2o.BaseTest;
import com.snnu.o2o.entity.PersonInfo;
import com.snnu.o2o.entity.WechatAuth;

public class WechatAutoDaoTest extends BaseTest{
	@Autowired
	private WechatAuthDao wechatAuthDao;
	
	@Test
	
	public void testInsertWechatAuth() throws Exception{
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId("zzzzzz");
		wechatAuth.setCreateTime(new Date());
		int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryWechatAuthByOpenId() throws Exception{
		WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("dadgdgdag");
		assertEquals("woaini",wechatAuth.getPersonInfo().getName());
	}
}

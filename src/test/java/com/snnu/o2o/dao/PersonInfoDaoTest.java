package com.snnu.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.snnu.o2o.BaseTest;
import com.snnu.o2o.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	@Ignore
	public void testInsertPersonInfo() throws Exception{
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("woaini");
		personInfo.setGender("女");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int effectedNum = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1,effectedNum);
	}
	
	@Test
	public void testQueryPersonInfoById() {
		long userId = 2;
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());
	}
}

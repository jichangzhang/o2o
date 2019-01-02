package com.snnu.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.snnu.o2o.BaseTest;
import com.snnu.o2o.dto.LocalAuthExecution;
import com.snnu.o2o.entity.LocalAuth;
import com.snnu.o2o.entity.PersonInfo;
import com.snnu.o2o.enums.WechatAuthStateEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest{
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testABindLocalAuth() {
		//新增一条平台账号
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String username = "testusername";
		String password = "testpassword";
		//给平台账号设置上用户信息
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);;
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		
		localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
		System.out.println("用户昵称:" + localAuth.getPersonInfo().getName());
		System.out.println("平台账号密码:" + localAuth.getPassword());
	}
	
	@Test
	public void testBModifyLocalAuth() {
		long userId = 1;
		String username = "testusername";
		String password = "testpassword";
		String newPassword = "testnewpassword";
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),lae.getState());
		//通过账号密码找到修改后的localAuth
		LocalAuth localAuth = localAuthService.getLocalAuthUsernameAndPwd(username, newPassword);
		System.out.println(localAuth.getPersonInfo().getName());
	}
}

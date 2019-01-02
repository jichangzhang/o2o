package com.snnu.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.snnu.o2o.BaseTest;
import com.snnu.o2o.dto.ImageHolder;
import com.snnu.o2o.dto.ShopExecution;
import com.snnu.o2o.entity.Area;
import com.snnu.o2o.entity.PersonInfo;
import com.snnu.o2o.entity.Shop;
import com.snnu.o2o.entity.ShopCategory;
import com.snnu.o2o.enums.ShopStateEnum;
import com.snnu.o2o.exception.ShopOperationException;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 3, 2);
		System.out.println("店铺列表数为:" + se.getShopList().size());
		System.out.println("店铺总数为:" + se.getCount());
	}

	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的店铺名称11");
		File shopImg = new File("/Users/work/image/timg.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("timg.jpg",is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println("新的图片地址:" + shopExecution.getShop().getShopImg());
	}

	@Test
	@Ignore
	public void testAddShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺996");
		shop.setShopDesc("test6899");
		shop.setShopAddr("test68999");
		shop.setPhone("test8");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("/Users/work/image/zjc.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
		ShopExecution se = shopService.addShop(shop, imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}

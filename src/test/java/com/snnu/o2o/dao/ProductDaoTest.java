package com.snnu.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.snnu.o2o.BaseTest;
import com.snnu.o2o.entity.Product;
import com.snnu.o2o.entity.ProductCategory;
import com.snnu.o2o.entity.ProductImg;
import com.snnu.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	@Ignore
	public void testInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(1L);
		// 初始化三个商品实例并添加进shopId为1的店铺里
		// 同时商品类别Id也为1
		Product product1 = new Product();
		product1.setProductName("ceshi1");
		product1.setProductDesc("cesihDesc1");
		product1.setImgAddr("test1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);

		Product product2 = new Product();
		product2.setProductName("ceshi2");
		product2.setProductDesc("cesihDesc2");
		product2.setImgAddr("test2");
		product2.setPriority(2);
		product2.setEnableStatus(1);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);

		Product product3 = new Product();
		product3.setProductName("ceshi3");
		product3.setProductDesc("cesihDesc3");
		product3.setImgAddr("test3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(pc1);

		// 判断添加是否成功
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);

	}

	@Test
	@Ignore
	public void testQueryProductById() throws Exception {
		long productId = 1;
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("tupian 1");
		productImg1.setImgDesc("tupian 1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(productId);
		
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("tupian 2");
		productImg2.setImgDesc("tupian 2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2,effectedNum);
		
		Product product = productDao.queryProductById(productId);
		assertEquals(4,product.getProductImgList().size());
		
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(4,effectedNum);

	}
	
	@Test
	@Ignore
	public void testUpdateProduct() throws Exception{
		Product product = new Product();
		ProductCategory productCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		productCategory.setProductCategoryId(1L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductName("第一个产品");
		product.setProductCategory(productCategory);
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryProductList() throws Exception{
		Product productCondition = new Product();
		List<Product> productList = productDao.queryProductList(productCondition, 0, 6);
		assertEquals(6,productList.size());
		int count = productDao.queryProductCount(productCondition);
		assertEquals(6,count);
		productCondition.setProductName("测试商品");
		productList = productDao.queryProductList(productCondition, 0, 6);
		assertEquals(2,productList.size());
		count = productDao.queryProductCount(productCondition);
		assertEquals(2,count);
	}
	
	@Test
	public void testUpdateProductCategoryToNull() {
		//将productCategoryId为2的商品类别下面的商品的商品类别置为空
		int effectedNum = productDao.updateProductCategoryToNull(4L);
		assertEquals(1,effectedNum);
	}
}

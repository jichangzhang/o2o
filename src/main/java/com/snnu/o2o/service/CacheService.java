package com.snnu.o2o.service;

public interface CacheService {
	
	/**
	 * 依据key前缀匹配该模式下的所有key-value 如传入:shopcategory,则shopcategory_allfirstlevel等
	 * 以shopcategory打头的key-value都会被清空
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}

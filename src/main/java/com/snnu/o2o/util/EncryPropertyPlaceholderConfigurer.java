package com.snnu.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	//需要加密的字段数组
	private String[] encryptyPropNames = {"jdbc.username","jdbc.password"};
	
	/**
	 * 对关键的属性进行转换
	 */
	@Override
	protected String convertProperty(String propertyName,String propertyValue) {
		if(isEncryptProp(propertyName)) {
			//对已加密的字段进行解密工作
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		}else {
			return propertyValue;
		}
	}

	/**
	 * 该属性是否加密
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName) {
		for(String encryptyProptyNames : encryptyPropNames) {
			if(encryptyProptyNames.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}

package com.palmg.boot.webcore.controller;

import com.palmg.boot.webcore.annotation.contorller.ReturnBind;

/**
 * 数据转换支持接口
 * @author chenkui
 *
 */
public interface ConverSupport {
	/**
	 * 数据转换方法。
	 * @param returnValue
	 * @return
	 */
	Result conver(Object returnValue, ReturnBind annot);
}

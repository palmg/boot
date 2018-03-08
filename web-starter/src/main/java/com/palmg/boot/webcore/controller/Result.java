package com.palmg.boot.webcore.controller;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 数据返回类型，统一数据返回结构
 * @author chenkui
 *
 */
@JsonInclude(Include.NON_NULL)
public interface Result extends Serializable{
	
}

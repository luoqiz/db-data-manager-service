package com.luoqiz.code.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper 继承自己的MyMapper，作为备用
 */
//public abstract class MybatisMapper<T> implements Mapper<T>, MySqlMapper<T> {

public interface MybatisMapper<T> extends Mapper<T>, MySqlMapper<T> {

	// TODO
	// FIXME 特别注意，该接口不能被扫描到，否则会出错
}
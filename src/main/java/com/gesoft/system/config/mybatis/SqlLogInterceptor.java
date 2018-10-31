package com.gesoft.system.config.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
* @ClassName sqlLogInterceptor 
* @Description TODO(语句拦截器) 
* @author lhh 
* @date 2014年5月20日 上午10:07:44 
*  
*/
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class })
	})
public class SqlLogInterceptor implements Interceptor {

	private Properties properties;

	/*
	* <p>Title intercept</p> 
	* <p>Description mybatis拦截器实现方法，打印语句</p> 
	* @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation) 
	*/
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		
		String sqlId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue = null;
		
		System.err.println(getSql(configuration, boundSql, sqlId, 0l));
		
		//long start = System.currentTimeMillis();
		returnValue = invocation.proceed();
		/*long end = System.currentTimeMillis();
		long time = (end - start);
		if (time > 1) {
			String sql = getSql(configuration, boundSql, sqlId, time);
			System.err.println(sql);
		}*/
		
		/*ResultMap rm = mappedStatement.getResultMaps().get(0);
		System.out.println("*****************************");
		System.out.println("*****************************");
		System.out.println(rm.getMappedColumns());
		System.out.println("-------------------");
		System.out.println(rm.getResultMappings());
		for(ResultMapping r : rm.getResultMappings()){
			System.out.println(r.getColumn());
		}
		
		System.out.println("*****************************");
		System.out.println("*****************************");*/
		
		return returnValue;
	}

	/** 
	* @Title com.ge.interceptor.mybatis.sqlLogInterceptor.getSql 
	* @Description TODO(日志格式组合) 
	* @param configuration  mybatis中参数
	* @param boundSql 		mybatis中参数
	* @param sqlId 			调用的语句名 例：com.ge.mapper.system.menu.MenuMapper.findMenuByRootNo
	* @param time  			执行时间
	* @return String    	返回类型 
	*/
	private String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
		
		StringBuilder str = new StringBuilder(100);
		
		try {
			String sql = showSql(configuration, boundSql);
			str = new StringBuilder(100);
			str.append("[MYBATIS-SQL]");
			str.append(" ");
			str.append(this.getDate());
			str.append(" ");
			str.append(sqlId);
			str.append(":==> ");
			str.append(sql);
			//str.append(" ");
			//str.append("(");
			//str.append(time);
			//str.append("ms");
			//str.append(")");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return str.toString();
	}

	/** 
	* @Title com.ge.interceptor.mybatis.sqlLogInterceptor.getParameterValue 
	* @Description TODO(格式化参数) 
	* @param obj 参数
	* @return String    返回类型 
	*/
	private String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	/** 
	* @Title com.ge.interceptor.mybatis.sqlLogInterceptor.showSql 
	* @Description TODO(整理mybatis语句，将语句中的参数替换到语句中的?处) 
	* @param configuration 	mybatis参数
	* @param boundSql		mybatis参数
	* @return String    返回类型 
	*/
	private String showSql(Configuration configuration, BoundSql boundSql) {
		
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		
		return sql;
	}
	
	/** 
    * @Title com.ge.interceptor.spring.getDate 
    * @Description TODO(获取当前日期) 
    * @return String    返回类型 
    */
    private String getDate(){
    	try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new GregorianCalendar().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
    }

	/*
	* <p>Title plugin</p> 
	* <p>Description </p> 
	* @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object) 
	*/
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/*
	* <p>Title setProperties</p> 
	* <p>Description </p> 
	* @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties) 
	*/
	@Override
	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}
}
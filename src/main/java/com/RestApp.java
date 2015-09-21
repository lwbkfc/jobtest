package com;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by lwb on 2015/9/20.
 */
public class RestApp extends ResourceConfig {
    public RestApp(){
        //服务类所在包路径
        packages("com.lwb.resource");
        //打印访问日志，便于跟踪调试，正式发布可清除
        register(JacksonJsonProvider.class);
        register(LoggingFilter.class);

    }

}















































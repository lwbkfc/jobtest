package com.lwb.resource;

import com.lwb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.SortedMap;

/**
 * Created by lwb on 2015/9/20.
 */
@Path("/user")
@Component
public class UserResource {
    @Resource
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
    @Autowired(required=false)
    public void setUserService(@Qualifier("userService")UserService userService) {
        this.userService = userService;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/say")
    public String sayHello(){
        return "Hello Jersey";
    }

    @GET
    @Produces("text/html")
    @Path("/list")
    public Response getUserList(){
        SortedMap[] sms = userService.getUserList();
        String html = "";
        html+="<html>";
        html+="<body>";
        html+="<table>";
        for (SortedMap sm:sms){
            html+="<tr>";
            html+="<td>";
            html+=sm.get("id");
            html+="</td>";
            html+="<td>";
            html+=sm.get("username");
            html+="</td>";
            html+="<td>";
            html+=sm.get("password");
            html+="</td>";
            html+="<td>";
            html+=sm.get("status");
            html+="</td>";
            html+="<td>";
            html+=sm.get("startTime");
            html+="</td>";
            html+="<td>";
            html+=sm.get("endTime");
            html+="</td>";
            html+="</tr>";
        }
        html+="</table>";
        html+="</body>";
        html+="</html>";
        return Response.ok(html).build();
    }
}




























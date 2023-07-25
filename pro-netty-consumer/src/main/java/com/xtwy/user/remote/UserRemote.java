package com.xtwy.user.remote;


import com.xtwy.client.param.Response;
import com.xtwy.user.bean.User;

public interface UserRemote {
	public Response saveUser(User user);

}

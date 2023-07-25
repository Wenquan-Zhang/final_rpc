package com.xtwy.user.remote;

import javax.annotation.Resource;

import com.xtwy.netty.annotation.Remote;
import com.xtwy.netty.util.Response;
import com.xtwy.netty.util.ResponseUtil;
import com.xtwy.user.model.User;
import com.xtwy.user.service.UserService;

@Remote
public class UserRemoteImpl implements UserRemote{

	@Resource
	UserService userService;
	
	public Object saveUser(User user) {
		userService.save(user);
		return ResponseUtil.createSuccessResult(user);
	}
}

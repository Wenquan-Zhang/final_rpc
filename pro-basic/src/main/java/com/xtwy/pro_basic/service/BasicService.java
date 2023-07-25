package com.xtwy.pro_basic.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xtwy.client.annotation.RemoteInvoke;
import com.xtwy.user.model.User;
import com.xtwy.user.remote.UserRemote;

@Service
public class BasicService {

	@RemoteInvoke
	private UserRemote userRemote;
	
	public void testSaveUser() {
		User u=new User();
		u.setId(1);
		u.setName("xhh");
		Object response=userRemote.saveUser(u);
		System.out.println(JSONObject.toJSONString(response));
	}
}

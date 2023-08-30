//package com.xtwy.pro_netty_rpc;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.xtwy.netty.annotation.RemoteInvoke;
//import com.xtwy.netty.client.ClientRequest;
//import com.xtwy.netty.client.TcpClient;
//import com.xtwy.netty.util.Response;
//import com.xtwy.user.bean.User;
//import com.xtwy.user.remote.UserRemote;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RemoteInvokingTest.class)
//@ComponentScan("com.xtwy")
//public class RemoteInvokingTest {
//	
//	@RemoteInvoke
//	private UserRemote userRemote;
//	
//	@Test
//	public void testSaveUser() {
//		User u =new User();
//		u.setId(1);
//		u.setName("hh");
//		
//		userRemote.saveUser(u);
//		
//	}
//
//}

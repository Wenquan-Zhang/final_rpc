//package com.xtwy.pro_netty_rpc;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
//
//import org.junit.Test;
//
//import com.xtwy.netty.client.ClientRequest;
//import com.xtwy.netty.client.TcpClient;
//import com.xtwy.netty.util.Response;
//import com.xtwy.user.bean.User;
//
//public class TestTcp {
//
//	@Test
//	public void testGetResponse() {
//		ClientRequest request=new ClientRequest();
//		request.setContent("test");
//		Response response=TcpClient.send(request);
//		System.out.println(response.getResult());
//	}
//	
//	@Test
//	public void testSaveUser() {
//		ClientRequest request =new ClientRequest();
//		User u =new User();
//		u.setId(1);
//		u.setName("hh");
//		
//		request.setCommand("com.xtwy.user.controller.UserController.saveUser");
//		request.setContent(u);
//		Response resp =TcpClient.send(request);
//		System.out.println(resp.getResult());
//		
//	}
//}

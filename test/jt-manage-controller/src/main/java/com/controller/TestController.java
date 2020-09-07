package com.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
 
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
//@Controller
@ServerEndpoint("/user/json/server")//通过/server就可以注册这个mysocket了
public class TestController {
private static int onlineCount=0;
	
	private static CopyOnWriteArraySet<TestController> socketSet=new CopyOnWriteArraySet<TestController>();
	
	private Session session;
	
	private String name;
	@OnOpen//连接成功
	public void onOpen(Session session) {
		this.session=session;
		socketSet.add(this);
		addOnlineCount();  //这里可以用变量++替代，但是哪个更好呢？
		this.name="用户"+onlineCount;//为用户加个名字
		try {
			this.sendMessage("欢迎您！你是"+this.name+"，开始聊天吧！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("有新连接加入！当前在线人数为"+onlineCount);//这里通用若用函数替代，哪个更好呢？getcount()
		
	}
	
	@OnClose//关闭
	public void onClose() {
		onlineCount-=1;
		socketSet.remove(this);
		System.out.println("一个用户离开了！当前在线人数"+onlineCount);//同上
	}
	
	@OnError//连接出错
	public void onError(Session session,Throwable error) {
		System.out.println("连接发生错误");
		error.printStackTrace();
	}
	
	@OnMessage//当客户端有消息传来时
	public void onMessage(String message,Session session) {
		System.out.println("来自客户端的消息，由"+this.name+"发出："+message);
		String mstemp;
		for (TestController mySocket : socketSet) {
			if(this.name.equals(mySocket.name)) { //处理客户端消息
				mstemp="我"+":"+message;
			}else {
				mstemp=this.name+":"+message;
			}
			try {
				mySocket.sendMessage(mstemp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
	}
	
	
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		
	}
	
	//串行化，防止并发时数据出错。
	public static synchronized void addOnlineCount() {
		onlineCount++;
	}
	
	public static synchronized void subOnlineCount() {
		onlineCount--;
	}
	
	//个人觉得这个函数不用
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}


}

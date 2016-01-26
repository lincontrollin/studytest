package com.lin.studytest.nio.timeserver.server;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import sun.misc.Unsafe;

public class TimerServer {
	
	
	private Selector selector;
	
	private Selector readSelector;
	
	private ServerSocketChannel serverSocketChannel;
	
	
	public TimerServer(int port) {
		try{
			selector = Selector.open();
			readSelector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port :" + port);
		}catch(Exception e){
			System.out.println("init error");
		}
		
	}
	
	/**
	 * 开始执行
	 * @throws Exception
	 */
	public void start()throws Exception{
		while(true){
			int result = selector.select(1000);
//			System.out.println("connect result = "+result);
			Set<SelectionKey> keys = selector.selectedKeys();
			//有了key才处理
			if(keys!=null && keys.size()>0){
				Iterator<SelectionKey> iterator = keys.iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					System.out.println("key:"+key.interestOps());
					iterator.remove();
					if(key!=null){
						try{
							handle(key);
						}catch(Exception e){
							if(key!=null){
								key.cancel();
								key.channel().close();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 处理
	 * @param key
	 * @throws Exception
	 */
	private void handle(SelectionKey key)throws Exception{
		if(key.isValid()){
			if(key.isAcceptable()){
				ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
				SocketChannel channel = serverChannel.accept();
				if(channel!=null){
					channel.configureBlocking(false);
					channel.register(readSelector, SelectionKey.OP_READ);
				}
				new TimerReadClient(readSelector).run();
		
			}
			if(key.isReadable()){
				SocketChannel readChannel = (SocketChannel)key.channel();
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				int result = readChannel.read(byteBuffer);
				StringBuilder content = new StringBuilder("at ").append(System.currentTimeMillis()).append(" we get content: ");
				if(result>0){
					byteBuffer.flip();
					byte[] readByte = new byte[byteBuffer.remaining()];
					byteBuffer.get(readByte);
					content.append(new String(readByte,"UTF-8"));
					byteBuffer.clear();
					result =readChannel.read(byteBuffer);
				}
				if(result==-1){
					key.cancel();
					readChannel.close();
				}
				System.out.println("get content:"+content.toString());
//				writeMsg(readChannel, content.toString());
			}
		}
	}
	
	
	private void writeMsg(SocketChannel channel,String msg)throws Exception{
		if(msg!=null && msg.trim().length()>0){
			byte[] writeBytes = msg.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(writeBytes.length);
			buffer.put(writeBytes);
			buffer.flip();
			int result = channel.write(buffer);
			System.out.println("write result = "+result);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		
		TimerServer timerServer = new TimerServer(8089);
		timerServer.start();
	}

}

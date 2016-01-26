package com.lin.studytest.nio.timeserver.client;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TimerClient {
	
	static Logger logger = LogManager.getLogger(TimerClient.class);
	
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1", 8089);
		
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		Reader reader = new InputStreamReader(socket.getInputStream());
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String line = br.readLine();
		int i =0;
		while(true){
//			System.out.println("input line = "+line);
			String line = "你的my message num="+i++;
			ByteBuffer buffer = ByteBuffer.allocate(1000);
			buffer.put(line.getBytes());
			buffer.flip();
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			byte []dst = new byte[buffer.arrayOffset()];
			buffer.get(dst);
			bos.write(dst);
			bos.flush();
//			writer.write(buffer.array());
//			writer.flush();
			
//			System.out.println(socket.isInputShutdown());
//			StringBuilder content = new StringBuilder();
//			line = br.readLine();
//			System.out.println(line);
//			char [] readbytes = new char[1024];
//			int len = 0;
//			while((len=reader.read(readbytes))>0){
//				System.out.println(new String(readbytes,0,len));
////				content.append(new String(readbytes,0,len));
////				System.out.println(content.toString());
////				line = br.readLine();
//			}
			Thread.sleep(700);
			if(i==10000000){
				break;
			}
//			br.reset();
//			line = br.readLine();
		}
		bos.close();
//		writer.close();
//		br.close();
		reader.close();
		socket.close();
		logger.info("");

	}

}

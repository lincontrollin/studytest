package com.lin.studytest.nio.timeserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyTimerHandler extends ByteToMessageDecoder{
	
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		int length =  in.readableBytes();
		byte []content = new byte[length];
		in.readBytes(content, 0, length);
		
		System.out.println(new String(content));
	}

}

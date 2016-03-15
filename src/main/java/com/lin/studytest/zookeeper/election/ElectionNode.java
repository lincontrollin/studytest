package com.lin.studytest.zookeeper.election;

/**
 * @author lin
 * @date   2016年3月14日
 * @version 1.0
 * 选举节点
 */
public interface ElectionNode {
	
	String NODE_SPACE="/";
	
	static final byte[] EMPTY_BYTES = new byte[0];
	
	/**
	 * 选举
	 */
	public void elect();
	
}

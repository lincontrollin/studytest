package com.lin.studytest.zookeeper.election;

import org.apache.commons.lang3.math.NumberUtils;

public class ElectionUtil {
	
	/**
	 * 根据节点获取序列号
	 * @param nodeName
	 * @return
	 */
	public static int fetchSeq(String nodeName){
		return NumberUtils.toInt(nodeName.substring(AbstractElectionNode.ELECTION_NODE_PRE.length()));
	}

}

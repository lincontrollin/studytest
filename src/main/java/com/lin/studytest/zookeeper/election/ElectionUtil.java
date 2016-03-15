package com.lin.studytest.zookeeper.election;

import org.apache.commons.lang.math.NumberUtils;

public class ElectionUtil {
	
	/**
	 * 根据节点获取序列号
	 * @param nodeName
	 * @param parantNodePath
	 * @return
	 */
	public static int fetchSeq(String nodeName){
		return NumberUtils.toInt(nodeName.substring(AbstractElectionNode.ELECTION_NODE_PRE.length()));
	}

}

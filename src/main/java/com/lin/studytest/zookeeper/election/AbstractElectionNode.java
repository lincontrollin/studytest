package com.lin.studytest.zookeeper.election;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractElectionNode implements ElectionNode,Watcher{
	
	public static final String ELECTION_NODE_PRE = "e_";
	
	protected static Logger logger = LoggerFactory.getLogger(AbstractElectionNode.class);
	
	/**
	 * 节点名称
	 */
	protected String nodeName;
	
	/**
	 * 节点路径
	 */
	protected String nodePath;
	
	/**
	 * 父节点路径
	 */
	protected String parentNodePath;
	
	/**
	 * 节点顺序号
	 */
	protected int seqNum;
	
	/**
	 * leader节点名字
	 */
	protected String leaderNodeName;
	
	/**
	 * 父节点数据的版本
	 */
	protected int version;
	
	/**
	 * 选举策略（默认最小seq算法）
	 */
	protected ElectionStrategy electionStrategy = ElectionStrategy.DEFUALT_ELECTIONSTRATEGY;
	
	/**
	 * zookeeper client
	 */
	protected ZooKeeper zooKeeper;
	
	public AbstractElectionNode(ZooKeeper zooKeeper,String parantNodePath) {
		this.zooKeeper = zooKeeper;
		this.parentNodePath = parantNodePath;
	}
	
	public AbstractElectionNode(ZooKeeper zooKeeper,String parantNodePath,ElectionStrategy stragegy) {
		this.zooKeeper = zooKeeper;
		this.parentNodePath = parantNodePath;
		this.electionStrategy = stragegy;
	}
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	protected void init()throws Exception{
		Stat stat = null;
		if((stat=zooKeeper.exists(parentNodePath, false))!=null){
			version = stat.getVersion();//获取父节点的数据的版本
			final AbstractElectionNode ae = this;
			zooKeeper.create(parentNodePath+NODE_SPACE+ELECTION_NODE_PRE,
					EMPTY_BYTES, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,new StringCallback() {
						@Override
						public void processResult(int rc, String path, Object ctx, String name) {
							try{
								switch (rc) {
									case Code.Ok:
										seqNum = NumberUtils.toInt(name.substring(path.length()));
										nodePath = name;
										nodeName = name.substring(parentNodePath.length()+1);
										watchNode(ae);
										break;
									default:
										break;
								}
							}catch(Exception e){
								logger.info("error in watchNode {}",e);
							}
						}
					},null);
		}
		
	}
	
	/**
	 * 监听
	 * @param watcher
	 * @throws Exception
	 */
	protected void watchNode(Watcher watcher)throws Exception{
		List<String> children = getChildren();
		String watchNode = this.nodeName;
		int minSeq = this.seqNum;
		//找到最小seq的node
		if(!CollectionUtils.isEmpty(children)){
			for(String child : children){
				if(child.equals(this.nodeName))
					continue;
				int seq = ElectionUtil.fetchSeq(child);
				if(seq<minSeq){
					minSeq = seq;
					watchNode = child;
				}
			}
		}
		//如果两个node不相等
		if(!watchNode.equals(this.nodeName)){
			zooKeeper.exists(parentNodePath+NODE_SPACE+watchNode, watcher);
		}
	}
	
	@Override
	public void elect() {
		try{
			if(electionStrategy.electAndResult(this)){
//				zooKeeper.setData(parentNodePath, nodeName.getBytes(), version);//将父节点的数据设置成成功选举节点的名称
				electLeader();
			}
			watchNode(this);
		}catch(Exception e){
			logger.info("elect error ",e);
		}
	}
	
	/**
	 * 选举成功的动作
	 * @throws Exception
	 */
	protected void electLeader()throws Exception{	
	}
	
	
	@Override
	public void process(WatchedEvent event) {
		logger.info("node event :{}",event);
		if(event.getType().equals(EventType.NodeDeleted)){
			elect();
		}
	}
	
	
	/**
	 * 获得父节点的children
	 * @return
	 * @throws Exception
	 */
	public List<String> getChildren()throws InterruptedException,KeeperException{
		return this.zooKeeper.getChildren(this.parentNodePath, false);
	}
	
	
	
	

}

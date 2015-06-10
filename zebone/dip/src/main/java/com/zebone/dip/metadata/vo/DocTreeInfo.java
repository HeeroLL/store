/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Jul 2, 2013
 */
package com.zebone.dip.metadata.vo;

import java.io.Serializable;
import java.util.List;

public class DocTreeInfo implements Serializable{
	/**  
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	/**文档主键*/
	private String docId;
	/**节点树相关信息*/
	private List<TreeInfo> treeInfos;
	
	/**保存文档结构时使用，已删除的节点映射id*/
	private String deleteTreeNodesId;
	
	public String getDeleteTreeNodesId() {
		return deleteTreeNodesId;
	}
	public void setDeleteTreeNodesId(String deleteTreeNodesId) {
		this.deleteTreeNodesId = deleteTreeNodesId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public List<TreeInfo> getTreeInfos() {
		return treeInfos;
	}
	public void setTreeInfos(List<TreeInfo> treeInfos) {
		this.treeInfos = treeInfos;
	}
}

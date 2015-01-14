package pojoControllerFlowConfig;

import java.util.List;

public class flowConfig {
	String installInHw;
	String name;
	node node;
	String ingressPort;
	String priority;
	String etherType;
	String vlanId;
	String vlanPriority;
	String nwSrc;
	String nwDst;
	String protocol;
	String idleTimeout;
	String hardTimeout;
	List <String> actions;
	
	public flowConfig(String installInHw, String name,
			pojoControllerFlowConfig.node node, String ingressPort,
			String priority, String etherType, String vlanId,
			String vlanPriority, String nwSrc, String nwDst, String protocol,
			String idleTimeout, String hardTimeout, List<String> actions) {
		
		this.installInHw = installInHw;
		this.name = name;
		this.node = node;
		this.ingressPort = ingressPort;
		this.priority = priority;
		this.etherType = etherType;
		this.vlanId = vlanId;
		this.vlanPriority = vlanPriority;
		this.nwSrc = nwSrc;
		this.nwDst = nwDst;
		this.protocol = protocol;
		this.idleTimeout = idleTimeout;
		this.hardTimeout = hardTimeout;
		this.actions = actions;
	}
	public String getInstallInHw() {
		return installInHw;
	}
	public void setInstallInHw(String installInHw) {
		this.installInHw = installInHw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public node getNode() {
		return node;
	}
	public void setNode(node node) {
		this.node = node;
	}
	public String getIngressPort() {
		return ingressPort;
	}
	public void setIngressPort(String ingressPort) {
		this.ingressPort = ingressPort;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getEtherType() {
		return etherType;
	}
	public void setEtherType(String etherType) {
		this.etherType = etherType;
	}
	public String getVlanId() {
		return vlanId;
	}
	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}
	public String getVlanPriority() {
		return vlanPriority;
	}
	public void setVlanPriority(String vlanPriority) {
		this.vlanPriority = vlanPriority;
	}
	public String getNwSrc() {
		return nwSrc;
	}
	public void setNwSrc(String nwSrc) {
		this.nwSrc = nwSrc;
	}
	public String getNwDst() {
		return nwDst;
	}
	public void setNwDst(String nwDst) {
		this.nwDst = nwDst;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getIdleTimeout() {
		return idleTimeout;
	}
	public void setIdleTimeout(String idleTimeout) {
		this.idleTimeout = idleTimeout;
	}
	public String getHardTimeout() {
		return hardTimeout;
	}
	public void setHardTimeout(String hardTimeout) {
		this.hardTimeout = hardTimeout;
	}
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
	
	
}

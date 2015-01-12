package flowController;

import java.util.List;

import wsPojoController.edgePropWs;

public class Flow {
	String name;
	String ingressPort;
	String priority;
	node node;
	String hardTimeout;
	String idleTimeout;
	String etherType;
	String vlanId;
	String vlanPriority;
	String nwSrc;
	String nwDst;
	String protocol;
	String installInHw;
	public List <String> actions;
	
	public Flow(String name, String ingressPort, String priority,
			flowController.node node, String hardTimeout, String idleTimeout,
			String etherType, String vlanId, String vlanPriority, String nwSrc,
			String nwDst, String protocol, String installInHw,
			List<String> actions) {
		super();
		this.name = name;
		this.ingressPort = ingressPort;
		this.priority = priority;
		this.node = node;
		this.hardTimeout = hardTimeout;
		this.idleTimeout = idleTimeout;
		this.etherType = etherType;
		this.vlanId = vlanId;
		this.vlanPriority = vlanPriority;
		this.nwSrc = nwSrc;
		this.nwDst = nwDst;
		this.protocol = protocol;
		this.installInHw = installInHw;
		this.actions = actions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public node getNode() {
		return node;
	}
	public void setNode(node node) {
		this.node = node;
	}
	public String getHardTimeout() {
		return hardTimeout;
	}
	public void setHardTimeout(String hardTimeout) {
		this.hardTimeout = hardTimeout;
	}
	public String getIdleTimeout() {
		return idleTimeout;
	}
	public void setIdleTimeout(String idleTimeout) {
		this.idleTimeout = idleTimeout;
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
	public String getInstallInHw() {
		return installInHw;
	}
	public void setInstallInHw(String installInHw) {
		this.installInHw = installInHw;
	}
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
}

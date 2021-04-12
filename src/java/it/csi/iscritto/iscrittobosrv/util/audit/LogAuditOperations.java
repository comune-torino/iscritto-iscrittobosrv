package it.csi.iscritto.iscrittobosrv.util.audit;

public enum LogAuditOperations {
	// @formatter:off
	LOGIN ("login"),
	LOGOUT("logout"),
	READ  ("read"),
	INSERT("insert"),
	UPDATE("update"),
	DELETE("delete"),
	MERGE ("merge"),
	;
	// @formatter:on

	private String operationName;

	private LogAuditOperations(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationName() {
		return operationName;
	}

}

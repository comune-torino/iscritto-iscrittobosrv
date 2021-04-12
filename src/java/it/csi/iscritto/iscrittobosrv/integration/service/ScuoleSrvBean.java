package it.csi.iscritto.iscrittobosrv.integration.service;

import it.csi.iscritto.serviscritto.interfacecsi.scuole.ScuoleSrv;

public class ScuoleSrvBean {
	private ScuoleSrv serviceInterface;

	public ScuoleSrv getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(ScuoleSrv serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

}

package it.csi.iscritto.iscrittobosrv.integration.service;

import it.csi.iscritto.serviscritto.interfacecsi.profilazione.ProfilazioneSrv;

public class ProfilazioneSrvBean {
	private ProfilazioneSrv serviceInterface;

	public ProfilazioneSrv getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(ProfilazioneSrv serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

}

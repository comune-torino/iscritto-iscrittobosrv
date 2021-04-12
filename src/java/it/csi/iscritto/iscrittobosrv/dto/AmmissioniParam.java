package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AmmissioniParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Long> idClasseList = new ArrayList<>();
	private Boolean value;

	public List<Long> getIdClasseList() {
		return idClasseList;
	}

	public void setIdClasseList(List<Long> idClasseList) {
		this.idClasseList = idClasseList;
	}

	public Boolean isValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

}

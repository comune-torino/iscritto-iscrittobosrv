package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Richiedente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String telefono;
	private String residenzaFutura;
	private String recapitoNoResidenza;
	private String relazioneConMinore;
	private String condizioneCoabitazione;
	private Anagrafica anagrafica;
	private LuogoNascita luogoNascita;
	private Residenza residenza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getResidenzaFutura() {
		return residenzaFutura;
	}

	public void setResidenzaFutura(String residenzaFutura) {
		this.residenzaFutura = residenzaFutura;
	}

	public String getRecapitoNoResidenza() {
		return recapitoNoResidenza;
	}

	public void setRecapitoNoResidenza(String recapitoNoResidenza) {
		this.recapitoNoResidenza = recapitoNoResidenza;
	}

	public String getRelazioneConMinore() {
		return relazioneConMinore;
	}

	public void setRelazioneConMinore(String relazioneConMinore) {
		this.relazioneConMinore = relazioneConMinore;
	}

	public String getCondizioneCoabitazione() {
		return condizioneCoabitazione;
	}

	public void setCondizioneCoabitazione(String condizioneCoabitazione) {
		this.condizioneCoabitazione = condizioneCoabitazione;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public LuogoNascita getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(LuogoNascita luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Residenza getResidenza() {
		return residenza;
	}

	public void setResidenza(Residenza residenza) {
		this.residenza = residenza;
	}

}

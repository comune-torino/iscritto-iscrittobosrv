package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Minore implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean presenzaNucleo;
	private Boolean residenzaConRichiedente;
	private Anagrafica anagrafica;
	private LuogoNascita luogoNascita;
	private Residenza residenza;
	private Disabilita disabilita;
	private ServiziSociali serviziSociali;
	private ProblemiSalute problemiSalute;
	private FratelloFrequentante fratelloFrequentante;
	private Trasferimento trasferimento;
	private Spostamento spostamento;

	private String cinqueAnniNonFrequentante;
	private ListaAttesa listaAttesa;
	private FratelloNidoContiguo fratelloNidoContiguo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPresenzaNucleo() {
		return presenzaNucleo;
	}

	public void setPresenzaNucleo(Boolean presenzaNucleo) {
		this.presenzaNucleo = presenzaNucleo;
	}

	public Boolean getResidenzaConRichiedente() {
		return residenzaConRichiedente;
	}

	public void setResidenzaConRichiedente(Boolean residenzaConRichiedente) {
		this.residenzaConRichiedente = residenzaConRichiedente;
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

	public Disabilita getDisabilita() {
		return disabilita;
	}

	public void setDisabilita(Disabilita disabilita) {
		this.disabilita = disabilita;
	}

	public ServiziSociali getServiziSociali() {
		return serviziSociali;
	}

	public void setServiziSociali(ServiziSociali serviziSociali) {
		this.serviziSociali = serviziSociali;
	}

	public ProblemiSalute getProblemiSalute() {
		return problemiSalute;
	}

	public void setProblemiSalute(ProblemiSalute problemiSalute) {
		this.problemiSalute = problemiSalute;
	}

	public FratelloFrequentante getFratelloFrequentante() {
		return fratelloFrequentante;
	}

	public void setFratelloFrequentante(FratelloFrequentante fratelloFrequentante) {
		this.fratelloFrequentante = fratelloFrequentante;
	}

	public Trasferimento getTrasferimento() {
		return trasferimento;
	}

	public void setTrasferimento(Trasferimento trasferimento) {
		this.trasferimento = trasferimento;
	}

	public Spostamento getSpostamento() {
		return spostamento;
	}

	public void setSpostamento(Spostamento spostamento) {
		this.spostamento = spostamento;
	}

	public String getCinqueAnniNonFrequentante() {
		return cinqueAnniNonFrequentante;
	}

	public void setCinqueAnniNonFrequentante(String cinqueAnniNonFrequentante) {
		this.cinqueAnniNonFrequentante = cinqueAnniNonFrequentante;
	}

	public ListaAttesa getListaAttesa() {
		return listaAttesa;
	}

	public void setListaAttesa(ListaAttesa listaAttesa) {
		this.listaAttesa = listaAttesa;
	}

	public FratelloNidoContiguo getFratelloNidoContiguo() {
		return fratelloNidoContiguo;
	}

	public void setFratelloNidoContiguo(FratelloNidoContiguo fratelloNidoContiguo) {
		this.fratelloNidoContiguo = fratelloNidoContiguo;
	}

}

package br.org.funcate.fachadas;

import java.io.File;

public class FotoFachada 
{
	private String inscricao;
	private String setor;
	private String quadra;
	private String logradouro;
	private String lote;
	private String sublote;
	private File foto;
	
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
		this.decodeInscricao();
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getSublote() {
		return sublote;
	}
	public void setSublote(String sublote) {
		this.sublote = sublote;
	}
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	
	private void decodeInscricao()
	{
		if(this.inscricao!=null)
		{
			setSetor(inscricao.substring(0,2));					
			setQuadra(inscricao.substring(2,7));
			setLogradouro(inscricao.substring(7,11));
			setLote(inscricao.substring(11,16));
			setSublote(inscricao.substring(16,19));
		}
	}
	@Override
	public String toString() {
		return "FotoFachada [inscricao=" + inscricao + ", setor=" + setor + ", quadra=" + quadra + ", logradouro="
				+ logradouro + ", lote=" + lote + ", sublote=" + sublote + ", foto=" + foto + "]";
	}
	
}

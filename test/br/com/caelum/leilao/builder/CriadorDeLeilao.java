package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class CriadorDeLeilao {
	
	private Leilao leilao;

	public CriadorDeLeilao(String descricao) {
		this.leilao = new Leilao(descricao);
	}
	
	public CriadorDeLeilao para(Lance lance) {
		this.leilao.propoe(lance);
		return this;
	}

	public Leilao constroi() {
		return this.leilao;
	}

}

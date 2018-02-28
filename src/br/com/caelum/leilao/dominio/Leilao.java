package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdLancesDo(usuario) < 5;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	private long qtdLancesDo(Usuario usuario) {
		return lances.stream()
				.filter(l -> l.getUsuario().equals(usuario))
				.count();
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		List<Lance> lancesDoUsuario = lances
										.stream()
										.filter(l -> l.getUsuario().equals(usuario))
										.collect(Collectors.toList());
		
		if(!lancesDoUsuario.isEmpty()) {
			Lance ultimoLance = lancesDoUsuario.get(lancesDoUsuario.size()-1);
			double valorDoLanceDobrado = ultimoLance.getValor() * 2;
			Lance lanceDobrado = new Lance(usuario, valorDoLanceDobrado);
			
			this.propoe(lanceDobrado);
		}
	}

}

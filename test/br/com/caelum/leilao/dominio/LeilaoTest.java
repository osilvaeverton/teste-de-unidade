package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void naoDeveAceitar2LancesEmSequenciaPeloMesmoUsuario() {
		
		Leilao leilao = new Leilao("Playstation 4");
		
		Usuario usuario = new Usuario("Everton");

		Lance lance = new Lance(usuario, 1000);
		Lance lance1 = new Lance(usuario, 2000);

		leilao.propoe(lance);
		leilao.propoe(lance1);
		
		assertEquals(1, leilao.getLances().size());
	}

	@Test
	public void naoDeveDarMaisDe5LancesComMesmoUsuario() {
		
		Leilao leilao = new Leilao("Playstation 4");
		
		Usuario usuario = new Usuario("Everton");
		Usuario usuario2 = new Usuario("Everton2");

		Lance lance = new Lance(usuario, 1000);
		Lance lance1 = new Lance(usuario2, 2000);

		Lance lance2 = new Lance(usuario, 2000);
		Lance lance3 = new Lance(usuario2, 2000);
		
		Lance lance4 = new Lance(usuario, 2000);
		Lance lance5 = new Lance(usuario2, 2000);
		
		Lance lance6 = new Lance(usuario, 2000);
		Lance lance7 = new Lance(usuario2, 2000);
		
		Lance lance8 = new Lance(usuario, 2000);
		Lance lance9 = new Lance(usuario2, 2000);
		
		Lance lance10 = new Lance(usuario, 2000);

		leilao.propoe(lance);
		leilao.propoe(lance1);
		leilao.propoe(lance2);
		leilao.propoe(lance3);
		leilao.propoe(lance4);
		leilao.propoe(lance5);
		leilao.propoe(lance6);
		leilao.propoe(lance7);
		leilao.propoe(lance8);
		leilao.propoe(lance9);
		leilao.propoe(lance10);

		assertEquals(10, leilao.getLances().size());
	}
	
	@Test
	public void deveEncontrarOUltimoLanceDadoDoUsuarioEDobrar() {
		Usuario usuario = new Usuario("Everton");
		Usuario usuario2 = new Usuario("Everton2");
		
		Lance lance = new Lance(usuario, 800);
		Lance lance2 = new Lance(usuario2, 900);

		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(lance);
		leilao.propoe(lance2);
		leilao.dobraLance(usuario);
		
		List<Lance> lances = leilao.getLances();
		assertEquals(3, lances.size());
		assertEquals(1600, lances.get(lances.size()-1).getValor(), 0.0001);
		
	}
	
    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }

}

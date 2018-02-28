package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador avaliador;
	
	@Before
	public void setUp() {
		this.avaliador = new Avaliador();
	}

	@Test
	public void testaMaiorEMenorValorDosLancesDoLeilao() {
		Usuario usuario = new Usuario("Everton");
		Lance lance1 = new Lance(usuario , 1000.0);

		Usuario usuario2 = new Usuario("Everton2");
		Lance lance2 = new Lance(usuario2 , 2000.0);

		Usuario usuario3 = new Usuario("Everton3");
		Lance lance3 = new Lance(usuario3 , 3000.0);
		
		Leilao leilao = new CriadorDeLeilao("Playstation 4")
								.para(lance1)
								.para(lance2)
								.para(lance3)
								.constroi();

		avaliador.avalia(leilao);
		
		assertEquals(1000.0, avaliador.getMenorLance(), 0.0001);
		assertEquals(3000.0, avaliador.getMaiorLance(), 0.0001);
	}

	@Test
	public void testaMediaDosLancesDoLeilao() {
		Usuario usuario = new Usuario("Everton");
		Lance lance1 = new Lance(usuario , 1000.0);

		Usuario usuario2 = new Usuario("Everton2");
		Lance lance2 = new Lance(usuario2 , 2000.0);

		Usuario usuario3 = new Usuario("Everton3");
		Lance lance3 = new Lance(usuario3 , 3000.0);

		Leilao leilao = new CriadorDeLeilao("Playstation 4")
				.para(lance1)
				.para(lance2)
				.para(lance3)
				.constroi();

		assertEquals(2000, avaliador.media(leilao), 0.0001);
	}
	
	@Test
	public void testaLeilaoComApenasUmLance() {
		Usuario usuario = new Usuario("Everton");
		Lance lance1 = new Lance(usuario , 1000.0);

		Leilao leilao = new CriadorDeLeilao("Playstation 4")
				.para(lance1)
				.constroi();

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(1000.0, avaliador.getMaiorLance(), 0.0001);
		assertEquals(1000.0, avaliador.getMenorLance(), 0.0001);
		assertEquals(1000.0, avaliador.media(leilao), 0.0001);
	}
	
	@Test
	public void testaLeilaoComCincoLances() {
		Usuario usuario = new Usuario("Everton");
		Usuario usuario2 = new Usuario("Everton");

		Lance lance1 = new Lance(usuario , 5000.0);
		Lance lance2 = new Lance(usuario2 , 1000.0);

		Lance lance3 = new Lance(usuario , 3800.0);
		Lance lance4 = new Lance(usuario2 , 200.0);

		Lance lance5 = new Lance(usuario , 6000.0);
		Lance lance6 = new Lance(usuario2 , 2000.0);
		
		Lance lance7 = new Lance(usuario , 4000.0);
		Lance lance8 = new Lance(usuario2 , 2000.0);
		
		Lance lance9 = new Lance(usuario , 5400.0);
		Lance lance10 = new Lance(usuario2 , 200.0);

		Leilao leilao = new CriadorDeLeilao("Playstation 4")
				.para(lance1)
				.para(lance2)
				.para(lance3)
				.para(lance4)
				.para(lance5)
				.para(lance6)
				.para(lance7)
				.para(lance8)
				.para(lance9)
				.para(lance10)
				.constroi();

		avaliador.avalia(leilao);
		
		assertEquals(3, avaliador.getTresMaiores().size());
		assertEquals(6000.0, avaliador.getTresMaiores().get(0).getValor(), 0.0001);
		assertEquals(5400.0, avaliador.getTresMaiores().get(1).getValor(), 0.0001);
		assertEquals(5000.0, avaliador.getTresMaiores().get(2).getValor(), 0.0001);
		
	}
	
	@Test
	public void testaLeilaoComDoisLances() {
		Usuario usuario = new Usuario("Everton");
		Usuario usuario2 = new Usuario("Everton2");

		Lance lance1 = new Lance(usuario , 5000.0);
		Lance lance2 = new Lance(usuario2 , 3800.0);

		Leilao leilao = new CriadorDeLeilao("Playstation 4")
				.para(lance1)
				.para(lance2)
				.constroi();

		avaliador.avalia(leilao);
		
		assertEquals(2, avaliador.getTresMaiores().size());
		assertEquals(5000.0, avaliador.getTresMaiores().get(0).getValor(), 0.0001);
		assertEquals(3800.0, avaliador.getTresMaiores().get(1).getValor(), 0.0001);
	}

	@Test
	public void testaLeilaoComNenhumLance() {
		Leilao leilao = new Leilao("Playstation 4");

		avaliador.avalia(leilao);
		
		assertEquals(0, avaliador.getTresMaiores().size());
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}

}

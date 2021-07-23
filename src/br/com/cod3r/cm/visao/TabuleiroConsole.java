package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoExcepition;
import br.com.cod3r.cm.excecao.SairExcepition;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}	

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra Partida? (S/n)");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		}catch (SairExcepition e) {
			System.out.println("bye");
		}finally {
			entrada.close();
		}
	}
	
	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capurarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capurarValorDigitado("1 - Abrir ou 2 - (Des)Marcar");
				
				if ("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
					
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Voc� Ganhou!");
		}catch(ExplosaoExcepition e) {
			System.out.println(tabuleiro);
			System.out.println("Voc� perdeu!");
		}
	}
	
	private String capurarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairExcepition();
		}
		return digitado;
	}
}

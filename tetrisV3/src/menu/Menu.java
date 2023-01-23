/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.util.Scanner;

import pieza.Pieza;
import pieza.TipoPieza;
import taulell.Taulell;

public class Menu {

	private static Scanner scan = new Scanner(System.in);

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
        /**
         * 
         * @param p pieza
         * @param amplada amplada del taulell
         */
	public static void MostrarPieza(Pieza p, int amplada) {
		for (int i = 0; i < p.getPosicion().length; i++) {
			for (int j = 0; j < amplada; j++) {
				if (j >= p.getColumna() && j < p.getColumna() + 4 && p.getCasilla(i, j - p.getColumna())) {
					switch (p.getTipo()) {
					case piezaI:
						System.out.print(ANSI_CYAN + "■");
						break;
					case piezaO:
						System.out.print(ANSI_YELLOW + "■");
						break;
					case piezaS:
						System.out.print(ANSI_GREEN + "■");
						break;
					case piezaL:
						System.out.print(ANSI_BLUE + "■");
						break;
					case piezaJ:
						System.out.print(ANSI_WHITE + "■");
						break;
					case piezaZ:
						System.out.print(ANSI_RED + "■");
						break;
					case piezaT:
						System.out.print(ANSI_PURPLE + "■");
						break;
					default:
						break;
					}

				} else {
					System.out.print("□");
				}
				System.out.print(" "+ANSI_RESET);
			}
			System.out.println();
		}
	}

	public static void MostrarTaulell(Taulell t) {
		for(int i = 0; i<t.getAmplada();i++) {
			System.out.print("- ");
		}
		System.out.println();
		TipoPieza[][] taulell = t.getTaulell();
		for (int i = 0; i < t.getTaulell().length; i++) {
			for (int j = 0; j < t.getTaulell()[0].length; j++) {
				switch (taulell[i][j]) {
				case piezaI:
					System.out.print(ANSI_CYAN + "■");
					break;
				case piezaO:
					System.out.print(ANSI_YELLOW + "■");
					break;
				case piezaS:
					System.out.print(ANSI_GREEN + "■");
					break;
				case piezaL:
					System.out.print(ANSI_BLUE + "■");
					break;
				case piezaJ:
					System.out.print(ANSI_WHITE + "■");
					break;
				case piezaZ:
					System.out.print(ANSI_RED + "■");
					break;
				case piezaT:
					System.out.print(ANSI_PURPLE + "■");
					break;
				case piezaNula:
					System.out.print("□");
				default:
					break;
				}
				System.out.print(" "+ANSI_RESET);
			}
			System.out.println(i==t.getTaulell().length-1? "Puntuacio: "+t.getPuntuacion():"");
		}
	}

	public static Taulell DefinirTaulell() {
		int amplada;
		int alcada;
		do {
		amplada = getInt("Introdueix l'amplada del taulell(minim 4): ");
		}while(amplada<4);
		do {
		alcada = getInt("Introdueix l'alcada del taulell(minim 4): ");
		}while(alcada<4);
		Taulell t = new Taulell(alcada, amplada);
		return t;
	}
	
	public static Pieza NovaPieza() {
		int dau = (int) (Math.random() * 7 + 1);
		Pieza p = null;
		switch (dau) {
		case 1:
			p = new Pieza(TipoPieza.piezaI);
			break;
		case 2:
			p = new Pieza(TipoPieza.piezaO);
			break;
		case 3:
			p = new Pieza(TipoPieza.piezaS);
			break;
		case 4:
			p = new Pieza(TipoPieza.piezaJ);
			break;
		case 5:
			p = new Pieza(TipoPieza.piezaL);
			break;
		case 6:
			p = new Pieza(TipoPieza.piezaZ);
			break;
		case 7:
			p = new Pieza(TipoPieza.piezaT);
			break;
		}
		return p;
	}

	public static Pieza ControlarPieza(Taulell t) {
		Pieza p = NovaPieza();
		boolean salir = false;
		do {
			clearScreen();
			MostrarPieza(p, t.getAmplada());
			MostrarTaulell(t);
			System.out.println("a: [←], d: [→], r: rotar s: llencar peca");
			switch (scan.nextLine().toLowerCase()) {
			case "a":
				p.moverIzq();
				break;
			case "d":
				p.moverDer(t.getAmplada());
				break;
			case "r":
				p.rotar(t.getAmplada());
				break;
			case "s":
				salir = true;
				break;
			default:
				break;
			}
		} while (!salir);
		return p;
	}

	/**
	 * Pide un valor de tipo Int
	 * 
	 * @param pedirEntero Texto que se muestra por pantalla para pedir un entero
	 * @return Devuelve un valor entero
	 */
	public static int getInt(String f) {
		int numero = 0;
		boolean valido = false;
		while (!valido) {
			System.out.print(f);
			try {
				numero = scan.nextInt();
				scan.nextLine();
				valido = true;
			} catch (Exception e) {
				scan = new Scanner(System.in);
				System.out.println("Introdueix un valor correcte");
			}
		}

		return numero;
	}

	public static void clearScreen() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n");
		System.out.flush();
	}
}

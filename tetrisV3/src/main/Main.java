/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import menu.*;
import pieza.*;
import taulell.Taulell;

public class Main {

	public static void main(String[] args) {
		Taulell t = Menu.DefinirTaulell();
		boolean finalPartida = false;
		while (!finalPartida) {
			Pieza p = Menu.ControlarPieza(t);
			finalPartida = t.FerCaureLaPieza(p);
			t.ComprobarFilas();
		}
		System.out.println("Fi de la partida");
		System.out.println("Puntuacio: "+t.getPuntuacion());
	}

}
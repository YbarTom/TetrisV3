/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taulell;

import pieza.*;

public class Taulell {

	private TipoPieza[][] _matriu;
	private int _amplada;
	private int _puntuacion;

	public Taulell(int alcada, int amplada) {
		this._matriu = new TipoPieza[alcada][amplada];
		this._amplada = amplada;
		this._puntuacion = 0;
		for (int i = 0; i < this._matriu.length; i++) {
			for (int j = 0; j < this._matriu[i].length; j++) {
				this._matriu[i][j] = TipoPieza.piezaNula;
			}
		}
	}

	public TipoPieza[][] getTaulell() {
		return this._matriu;
	}

	public int getAmplada() {
		return this._amplada;
	}

	public int getPuntuacion() {
		return this._puntuacion;
		
	}
	
	private int BuscarPosicio(Pieza p) {
		boolean[][] posicionPieza = p.getPosicion();
		int altura = -1;
		boolean ocupado = false;
		for (int i = p.getAlto() - 1; i < this._matriu.length && !ocupado; i++) {
			for (int j = 0; j < posicionPieza.length; j++) {
				for (int k = 0; k < posicionPieza.length; k++) {
					if (posicionPieza[j][k] && this._matriu[j + i - 3][p.getColumna() + k] != TipoPieza.piezaNula) {
						altura = i - 1;
						ocupado = true;
					}
				}
			}
			if (altura == -1 && i == this._matriu.length - 1) {
				altura = i;
			}
		}
		return altura;
	}

	public boolean FerCaureLaPieza(Pieza p) {
		boolean[][] posicionPieza = p.getPosicion();
		boolean ocupado = false;

		int altura = BuscarPosicio(p);
		if (altura < p.getAlto() - 1) {
			ocupado = true;
		} else {
			this._puntuacion+=1;
			for (int j = posicionPieza.length - 1; j >= 0; j--) {
				for (int k = 0; k < p.getAncho(); k++) {
					if (posicionPieza[j][k]) {
						this._matriu[altura][p.getColumna() + k] = p.getTipo();
					}
				}
				altura--;
			}
		}
		return ocupado;
	}

	public void ComprobarFilas() {
		for (int i = 0; i < this._matriu.length; i++) {
			int contador = 0;
			for (int j = 0; j < this._amplada; j++) {
				if (this._matriu[i][j] != TipoPieza.piezaNula) {
					contador++;
				}
			}
			if (contador == this._amplada) {
				this._puntuacion+=10;
				EliminarFila(i);
				ActualitzarTaulell(i);
			}
		}
	}

	public void ActualitzarTaulell(int fila) {
		for (int j = fila; j >= 0; j--) {
			for (int k = 0; k < this._matriu[0].length; k++) {
				if (this._matriu[j][k] != TipoPieza.piezaNula && this._matriu[j + 1][k] == TipoPieza.piezaNula) {
					this._matriu[j + 1][k] = this._matriu[j][k];
					this._matriu[j][k] = TipoPieza.piezaNula;
				}
			}
		}
	}

	public void EliminarFila(int fila) {
		for (int i = 0; i < this._amplada; i++) {
			this._matriu[fila][i] = TipoPieza.piezaNula;
		}
	}
}

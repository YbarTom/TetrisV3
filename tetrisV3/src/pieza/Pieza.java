/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieza;

import pieza.TipoPieza;

public class Pieza {

	private TipoPieza _tipo;
	private boolean[][] _posicion;
	private int _columna;
        /**
         * Asigna las dimensiones a las piezas
         * 
         * @param tipo tipo de pieza
         */
	public Pieza(TipoPieza tipo) {
		this._columna = 0;
		this._tipo = tipo;
		this._posicion = new boolean[4][4];
		switch (tipo) {
		case piezaI:// PiezaI
			for (int i = 0; i < 4; i++) {
				this._posicion[i][0] = true;
			}
			break;
		case piezaO:// PiezaO
			this._posicion[3][0] = true;
			this._posicion[3][1] = true;
			this._posicion[2][0] = true;
			this._posicion[2][1] = true;
			break;
		case piezaS:// PiezaS
			this._posicion[3][0] = true;
			this._posicion[3][1] = true;
			this._posicion[2][1] = true;
			this._posicion[2][2] = true;
			break;
		case piezaJ:// PiezaJ
			this._posicion[3][0] = true;
			this._posicion[3][1] = true;
			this._posicion[2][1] = true;
			this._posicion[1][1] = true;
			break;
		case piezaL:// PiezaL
			this._posicion[3][0] = true;
			this._posicion[2][0] = true;
			this._posicion[1][0] = true;
			this._posicion[3][1] = true;
			break;
		case piezaZ:// PiezaZ
			this._posicion[3][1] = true;
			this._posicion[3][2] = true;
			this._posicion[2][0] = true;
			this._posicion[2][1] = true;
			break;
		case piezaT:// PiezaT
			this._posicion[3][0] = true;
			this._posicion[3][1] = true;
			this._posicion[3][2] = true;
			this._posicion[2][1] = true;
			break;

		default:
			break;
		}
	}
        /**
         * 
         * @return 
         */
	public boolean[][] getPosicion() {
		return this._posicion;
	}
        /**
         * Posicion casilla
         * @param i
         * @param j
         * @return posicion casilla
         */
	public boolean getCasilla(int i, int j) {
		return this._posicion[i][j];
	}
        /**
         * Conseguir columna
         * @return posicion columna
         */
	public int getColumna() {
		return this._columna;
	}
        /**
         * Tipo de pieza
         * @return tipo de pieza
         */
	public TipoPieza getTipo() {
		return this._tipo;
	}
        /**
         * Mover figura izquierda
         */
	public void moverIzq() {
		if (this._columna > 0) {
			this._columna -= 1;
		}
	}
        /**
         * Mover figura derecha
         * @param tamTaulell 
         */
	public void moverDer(int tamTaulell) {
		if (tamTaulell - this._columna > getAncho()) {
			this._columna += 1;
		}
	}
        /**
         * Obtener ancho figura(por columnas)
         * @return 
         */
	public int getAncho() {
		int contador = 0;
		for (int i = 0; i < _posicion.length; i++) {
			boolean salir = false;
			for (int j = 0; j < _posicion.length && !salir; j++) {
				if (this._posicion[j][i]) {
					contador++;
					salir = true;
				}
			}
		}
		return contador;
	}
	/**
         * Obtiene el alto de la figura (por filas)
         * @return 
         */
	public int getAlto() {
		int contador = 0;
		for (int i = 0; i < _posicion.length; i++) {
			boolean salir = false;
			for (int j = 0; j < _posicion.length && !salir; j++) {
				if (this._posicion[i][j]) {
					contador++;
					salir = true;
				}
			}
		}
		return contador;
	}
        /**
         * Rota las matrices de las figuras
         * @param amplada 
         */
	public void rotar(int amplada) {
		if (_posicion == null || _posicion.length == 0) {
			return;
		}

		int N = _posicion.length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				boolean temp = _posicion[i][j];
				_posicion[i][j] = _posicion[j][i];
				_posicion[j][i] = temp;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N / 2; j++) {
				boolean temp = _posicion[i][j];
				_posicion[i][j] = _posicion[i][N - j - 1];
				_posicion[i][N - j - 1] = temp;
			}
		}

		reposicionar();
		this._columna -= Math.max(getAncho()+this._columna-amplada, 0);

	}
	/**
         * mira si hay hueco abajo de la figura
         * @return 
         */
	private int huecoAbajo() {
		int desplazarAbajo = 0;
		boolean salir = false;
		for (int i = _posicion.length - 1; i >= 0 && !salir; i--) {
			int contador = 0;
			for (int j = 0; j < _posicion.length && !salir; j++) {
				if (!_posicion[i][j]) {
					contador++;
				} else {
					salir = true;
				}
			}
			if (contador == _posicion.length) {
				desplazarAbajo++;
			}
		}
		return desplazarAbajo;
	}
	/**
         * 
         * @return 
         */
	private int huecoIzq() {
		int desplazarIzq = 0;
		boolean salir = false;
		for (int i = 0; i < _posicion.length && !salir; i++) {
			int contador = 0;
			for (int j = _posicion.length - 1; j >= 0 && !salir; j--) {
				if (!_posicion[j][i]) {
					contador++;
				} else {
					salir = true;
				}
			}
			if (contador == 4) {
				desplazarIzq++;
			}
		}
		return desplazarIzq;
	}
        /**
         * 
         */
	private void reposicionar() {
		// Desplazamientos hacia abajo
		int desplazarAbajo = huecoAbajo();
		// Desplazamientos hacia la izquierda
		int desplazarIzq = huecoIzq();
		// Mover
		boolean[][] aux = new boolean[_posicion.length][_posicion.length];
		for (int i = _posicion.length - 1; i >= 0; i--) {
			for (int j = 0; j < _posicion.length 
					&& i - desplazarAbajo >= 0 && j + desplazarIzq < _posicion.length; j++) {
				aux[i][j] = _posicion[i - desplazarAbajo][j + desplazarIzq];
			}
		}
		_posicion = aux;
	}
}

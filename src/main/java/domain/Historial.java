package domain;

import javax.persistence.Entity;

@Entity
public class Historial {
	

		private float DineroGanado;
		private int NumeroApuestasTerminadas;
		private int ApuestasGanadas;
		private int ApuestasPerdidas;
		private int CombinadaGanadas;
		private int CombinadasPerdidas;
		
		public int getCombinadaGanadas() {
			return CombinadaGanadas;
		}

		public void setCombinadaGanadas(int combinadaGanadas) {
			CombinadaGanadas = combinadaGanadas;
		}

		public int getCombinadasPerdidas() {
			return CombinadasPerdidas;
		}

		public void setCombinadasPerdidas(int combinadasPerdidas) {
			CombinadasPerdidas = combinadasPerdidas;
		}

		public Historial(float DineroGanado, int NumeroApuestasTerminadas, int ApuestasGanadas, int ApuestasPerdidas,  int DineroInvertido) {
			this.ApuestasGanadas= ApuestasGanadas;
			this.ApuestasPerdidas=ApuestasPerdidas;
			this.DineroGanado= DineroGanado;
			this.NumeroApuestasTerminadas=NumeroApuestasTerminadas;
		}

		public float getDineroGanado() {
			return DineroGanado;
		}

		public void setDineroGanado(float dineroGanado) {
			DineroGanado = dineroGanado;
		}

		public int getNumeroApuestas() {
			return NumeroApuestasTerminadas;
		}

		public void setNumeroApuestas(int numeroApuestas) {
			NumeroApuestasTerminadas = numeroApuestas;
		}

		public int getApuestasGanadas() {
			return ApuestasGanadas;
		}

		public void setApuestasGanadas(int apuestasGanadas) {
			ApuestasGanadas = apuestasGanadas;
		}

		public int getApuestasPerdidas() {
			return ApuestasPerdidas;
		}

		public void setApuestasPerdidas(int apuestasPerdidas) {
			ApuestasPerdidas = apuestasPerdidas;
		}

		
		
}

import java.util.Scanner;

public class Juego {

    public void inicioPartida() {

        Scanner sc = new Scanner(System.in);

        String nombre;

        Ficha ficha1 = new Ficha('X');
        Ficha ficha2 = new Ficha('O');

        System.out.print("Nombre del jugador 1: ");
        nombre = sc.next();

        System.out.println();

        Jugador jugador1 = new Jugador(nombre, ficha1.getFicha());

        System.out.print("Nombre del jugador 2: ");
        nombre = sc.next();

        System.out.println();

        Jugador jugador2 = new Jugador(nombre, ficha2.getFicha());

        System.out.println();

        Tablero tablero = new Tablero();

        boolean partida = true;

        while (partida) {

            tablero.imprimirTablero();

            int posicion = tablero.posicionValida(jugador1, sc);

            tablero.colocarFicha(posicion, jugador1.getFicha());

            if (tablero.comprobarVictoria(jugador1)) {
                break;
            }

            if (tablero.comprobarEmpate(jugador1)) {
                break;
            }

            tablero.imprimirTablero();

            posicion = tablero.posicionValida(jugador2, sc);
            
            tablero.colocarFicha(posicion, jugador2.getFicha());

            if (tablero.comprobarVictoria(jugador2)) {
                break;
            }

            if (tablero.comprobarEmpate(jugador2)) {
                break;
            }
        }
    }
}

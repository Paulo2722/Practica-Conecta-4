import java.util.Scanner;

public class Tablero {
    private char[][] tablero;
    private final int filas = 6;
    private final int columnas = 7;
    private char blanco = ' ';


    public Tablero() {

        /*Este código crea la versión inicial del tablero, siendo este un 6 X 7 en el cual, sus casillas tienen los valores
         * iniciales de 'blanco'*/

        tablero = new char[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = blanco;
            }
        }
    }

    public boolean colocarFicha(int posicion, char ficha) {

        /*Este código nos permite situar nuestra ficha en la columna que seleccionemos empezando desde la fila de abajo*/

        for (int i = filas - 1; i >= 0; i--) {
            if (tablero[i][posicion] == blanco) {
                tablero[i][posicion] = ficha;

                return true;
            }
        }
        return false;
    }

    public int posicionValida(Jugador jugador, Scanner sc){

        /*Este código nos permite repetir la colocacion de una ficha en caso de que el valor que se introduzca no
        * este entre los valores establecidos*/

        int posicion;
        boolean numeroValido = false;

        do{
            System.out.println(jugador.getNombre() + ", seleccione una columna (0-6)");

            while(!sc.hasNextInt()){
                System.out.println("El valor introducido no es valido, por favor, seleccione otro");
                sc.next();
            }

            posicion = sc.nextInt();

            if (posicion >= 0 && posicion < columnas && tablero[0][posicion] == blanco) {
                numeroValido = true;

            } else {
                System.out.println("El valor introducido no es valido, por favor, seleccione otro");
            }

        } while(!numeroValido);

        return posicion;

    }

    public void imprimirTablero() {

        /*Este código imprime el tablero de juego pero con las fichas en sus respectivas casilals*/

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("[" + tablero[i][j] + "]");
            }
            System.out.println();
        }
    }

    public boolean comprobarVictoria(Jugador jugador) {

        //Este metodo nos sirve para identificar cuando se ha ganado una partida y finalizar con esta

        /*Primeramente creamos un char 'ficha' para almacenar la ficha del jugador, esto nos servirá para identificar
         * cuando un jugador a logrado encadenar 4 de SUS fichas en el tablero*/

        char ficha = jugador.getFicha();

        if (comprobarHorizontal(ficha)
            || comprobarVertical(ficha)
            || comprobarDiagonalDerecha(ficha)
            || comprobarDiagonalIzquierda(ficha)){

            imprimirTablero();
            System.out.println("Victoria de " + jugador.getNombre());
            return true;
        } else {
            return false;
        }
    }

    /*A continuación, todos los métodos que comprueban las diferentes posibilidades de victoria*/

    public boolean comprobarHorizontal(char ficha) {

        /*Este código, recorre todas las filas empezando desde arriba y todas las columnas empezando por la izquierda
         * Utilizamos un contador para que, cuando encontremos una ficha en una fila 'x' y una columna 'x', aumente el valor
         * de este y, en caso de encontrar 4 fichas que, esten segudias en la misma columna, obtendremos un ganador*/

        for (int i = 0; i < filas; i++) {

            int count = 0;

            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == ficha && tablero[i][j] != blanco) {

                    count = count + 1;

                } else {
                    count = 0;

                }
                if (count >= 4)
                    return true;

            }
        }
        return false;
    }

    public boolean comprobarVertical(char ficha) {

        /*Metodo igual que el anterior pero, en vez de comprobar horizontalmente, lo hace verticalmente*/

        for (int j = 0; j < columnas; j++) {

            int count = 0;

            for (int i = 0; i < filas; i++) {
                if (tablero[i][j] == ficha && tablero[i][j] != blanco) {

                    count = count + 1;

                } else {
                    count = 0;

                }

                if (count >= 4)
                    return true;
            }
        }
        return false;
    }

    public boolean comprobarDiagonalDerecha(char ficha) {

        /*Este código comprueba si el jugador ha obtenido una victoria mediante una diagonal hacia la derecha. Para ello,
        * usamos los bucles for con un -4 en 'filas' y un 3 en 'j' para asegurarse de que haya espacio para que se de la
        * condición, después de eso, establecemos mediante la condicion if las posiciones en las que tienen que estar
        * ubicadas las fichas para que se de la condición*/

        for (int i = 0; i <= filas - 4; i++){

            for (int j = 3; j < columnas; j++){

                if (tablero[i][j] == ficha
                    && (tablero[i + 1][j - 1] == ficha)
                    && (tablero[i + 2][j - 2] == ficha)
                    && (tablero[i + 3][j - 3] == ficha))

                    return true;
            }
        }
        return false;
    }

    public boolean comprobarDiagonalIzquierda(char ficha){

        /*Metodo igual que el anterior pero que comprueba la diagonal hacia la izquierda*/

        for (int i = 0; i <= filas - 4; i++){

            for (int j = 0; j < columnas - 4; j++){

                if (tablero[i][j] == ficha
                    && (tablero[i + 1][j + 1] == ficha)
                    && (tablero[i + 2][j + 2] == ficha)
                    && (tablero[i + 3][j + 3] == ficha))

                    return true;
            }
        }
        return false;
    }

    public boolean comprobarEmpate(Jugador jugador){

        /*Este código comprueba si en la fila más elevada de la tabla existen espacios en blanco, es decir, en los que se
        * puede depositar una ficha. Si no es el caso y no ha habido antes un ganador, el resultado será un empate*/

        for (int j = 0; j < columnas; j++){
            if (tablero[0][j] == blanco)
                return false;
        }
        System.out.println("Empate");
        return true;
    }
}
public class Jugador {
    private String nombre;
    private char ficha;


    public Jugador(String nombre, char ficha) {
        this.nombre = nombre;
        this.ficha = ficha;
    }

    //Getters y Setters de los atributos de la clase Jugador
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public char getFicha(){
        return ficha;
    }

    public void setFicha(char ficha){
        this.ficha = ficha;
    }
}

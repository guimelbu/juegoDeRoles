package Forms;

public class Usuarios {
    private String nombre;
    private String tipoPersonaje;
    private String tiempo;
    private String vidas;
    private String oro;
    private int puntos;

    public Usuarios(String nombre, String tipoPersonaje, String tiempo, String vidas, String oro, int puntos) {
        this.nombre = nombre;
        this.tipoPersonaje = tipoPersonaje;
        this.tiempo = tiempo;
        this.vidas = vidas;
        this.oro = oro;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public void setTipoPersonaje(String tipoPersonaje) {
        this.tipoPersonaje = tipoPersonaje;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getVidas() {
        return vidas;
    }

    public void setVidas(String vidas) {
        this.vidas = vidas;
    }

    public String getOro() {
        return oro;
    }

    public void setOro(String oro) {
        this.oro = oro;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Tipo: " + tipoPersonaje +
                ", Tiempo: " + tiempo +
                ", Vidas: " + vidas +
                ", Oro: " + oro +
                ", Puntos: " + puntos +
                ";\n";
    }
}

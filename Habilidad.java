public class Habilidad {
    private String nombre;
    private int costoMP;
    private TipoHabilidad tipo;
    private int efecto;

    public Habilidad(String nombre, int costoMP, TipoHabilidad tipo, int efecto) {
        this.nombre = nombre;
        this.costoMP = costoMP;
        this.tipo = tipo;
        this.efecto = efecto;
    }

    public String getNombre() { return nombre; }
    public int getCostoMP() { return costoMP; }
    public TipoHabilidad getTipo() { return tipo; }
    public int getEfecto() { return efecto; }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ", Costo MP: " + costoMP + ")";
    }
}

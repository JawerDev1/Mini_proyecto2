public class Heroe extends Personaje {

    private TipoHeroe tipoHeroe;

    public Heroe() {
        super(TipoPersonaje.HEROE, "Heroe", 40, 8, 9, 7, 6);
        this.tipoHeroe = TipoHeroe.HEROE;
    }

    public TipoHeroe getTipoHeroe() {
        return tipoHeroe;
    }

    public void ataqueEstandar(Personaje enemigo) {
        atacar(enemigo);
    }

    public void habilidadCura() {

        if (getMp() >= 3) {
            gastarMp(3);
            curar(12);
            System.out.println(getNombre() + " Usa cura y recupera 12 HP");

        } else {
            System.out.println(getNombre() + " Intenta usar cura pero no tiene suficiente MP");
        }
    }
}

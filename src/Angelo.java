public class Angelo extends Personaje {

    private TipoHeroe tipoHeroe;

    public Angelo() {
        super(TipoPersonaje.HEROE, "Angelo", 38, 12, 8, 6, 6);
        this.tipoHeroe = TipoHeroe.ANGELO;
    }

    public TipoHeroe getTipoHeroe() {
        return tipoHeroe;
    }

    public void ataqueNormal(Personaje enemigo) {
        atacar(enemigo);
    }

    public void habilidadCura() {

        if (getMp() >= 4) {
            gastarMp(4);
            curar(15);
            System.out.println(getNombre() + " Usa cura y recupera 15 HP");

        } else {
            System.out.println(getNombre() + " Intenta usar cura, pero no tiene suficiente MP");
        }
    }
}

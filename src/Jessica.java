import java.util.Random;

public class Jessica extends Personaje {

    private TipoHeroe tipoHeroe;
    private Random random = new Random();

    public Jessica() {
        super(TipoPersonaje.HEROE, "Jessica", 32, 14, 6, 5, 7);
        this.tipoHeroe = TipoHeroe.YESSICA;
    }

    public TipoHeroe getTipoHeroe() {
        return tipoHeroe;
    }

    public void ataqueNormal(Personaje enemigo) {
        atacar(enemigo);
    }

    public void frizz(Personaje enemigo) {

        if (getMp() >= 4) {
            gastarMp(4);
            int danioMagico = random.nextInt(6) + 10;
            enemigo.recibirDanio(danioMagico);
            System.out.println(getNombre() + " Lanza Frizz e inflinge " + danioMagico + " de daño mágico");

        } else {
            System.out.println(getNombre() + " No tiene suficiente MP para lanzar Frizz");
        }
    }
}

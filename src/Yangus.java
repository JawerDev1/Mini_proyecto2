import java.util.Random;

public class Yangus extends Personaje {

    private TipoHeroe tipoHeroe;
    private Random random = new Random();

    public Yangus() {
        super(TipoPersonaje.HEROE, "Yangus", 55, 0, 12, 9, 3);
        this.tipoHeroe = TipoHeroe.YANGUS;
    }

    public TipoHeroe getTipoHeroe() {
        return tipoHeroe;
    }

    public void ataqueNormal(Personaje enemigo) {
        atacar(enemigo);
    }

    public void golpePoderoso(Personaje enemigo) {

        int probabilidad = random.nextInt(100) + 1;
        if (probabilidad <= 80) {
            int danioBase = Math.max(1, getAtaque() - enemigo.getDefensa());
            int danioTotal = (int) (danioBase * 1.5);
            enemigo.recibirDanio(danioTotal);
            System.out.println(getNombre() + " Usa golpe poderoso e inflige " + danioTotal + " de daño");

        } else {
            System.out.println(getNombre() + " Falla golpe poderoso...");
        }
    }
}

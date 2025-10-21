public class Enemigo extends Personaje {
    private TipoEnemigo tipo;

    public Enemigo(TipoEnemigo tipo, String nombre, int hp, int mp, int ataque, int defensa, int velocidad) {
        super(nombre, hp, mp, ataque, defensa, velocidad);
        this.tipo = tipo;
    }

    public TipoEnemigo getTipo() {
        return this.tipo;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(getNombre() + " (" + this.tipo + ")");
        System.out.println("HP: " + getHp() + " | MP: " + getMp() +
                " | ATK: " + getAtaque() + " | DEF: " + getDefensa() +
                " | VEL: " + getVelocidad());
        System.out.println();
    }

    public String atacar(Heroe objetivo) { 
        int danio=getAtaque() -objetivo.getDefensa(); 
        if (danio<0) danio=0; objetivo.recibirDanio(danio); 
        return getNombre() +" ataca a "+objetivo.getNombre() +" causando "+danio+" de daño.\n"; }
}

public class Enemigo extends Personaje {
    
    private TipoEnemigo tipoEnemigo;

    public Enemigo(
        TipoEnemigo tipoEnemigo,
        String nombre,
        int hp,
        int mp,
        int ataque,
        int defensa,
        int velocidad
    ) {
        
        super(TipoPersonaje.ENEMIGO, nombre, hp, mp, ataque, defensa, velocidad);
        this.tipoEnemigo = tipoEnemigo;
    }

    public TipoEnemigo getTipoEnemigo() {
        return tipoEnemigo;
    }

    
    public void atacar(Personaje objetivo) {
        int danio = calcularDanio(objetivo);
        objetivo.recibirDanio(danio);
        System.out.println(getNombre() + " ataca a " + objetivo.getNombre() + " causando " + danio + " de daño.");
    }

    
    public int calcularDanio(Personaje objetivo) {
        
        int danio = getAtaque() - objetivo.getDefensa();
        if (danio < 1) {
            danio = 1;
        }
        return danio;
    }
}








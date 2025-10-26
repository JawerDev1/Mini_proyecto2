public class Slime extends Enemigo{

    public Slime(){
        super(TipoEnemigo.SLIME, "Slime",22, 0, 6, 3, 7);
    }

    public int calcularDanio(Personaje objetivo){

        int danio = 3 + (int)(Math.random() * 4);
        return danio;
    }
    
}

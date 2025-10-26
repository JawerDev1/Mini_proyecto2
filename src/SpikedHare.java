public class SpikedHare extends Enemigo{

    public SpikedHare(){
        super(TipoEnemigo.SPIKED_HARE, "spikedHare", 38, 0, 4, 6, 9);
    }

    public int calcularDanio(Personaje objetivo){

        int danio = 6 + (int)(Math.random() * 4);
        if(Math.random() <= 0.3){
        System.out.println(getNombre() + "Usa patada Giratoria");
        }
        return danio;
    }
}

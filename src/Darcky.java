public class Darcky extends Enemigo{

    public Darcky(){
        super(TipoEnemigo.DRACKY,"Dacky",28,0,8,5,8);
    }

    public int calcularDanio(Personaje objetivo){

        double prob = Math.random();
        int danioBase = 5 + (int)(Math.random() * 4);
        if(prob <= 0.7){
            danioBase += 2;
            System.out.println(getNombre() + "Usa picotazo");
        }
        return danioBase;
    }


}


public class PatyPunk extends Enemigo {

        public PatyPunk(){
            super(TipoEnemigo.PATYPUNK, "Patypunk", 45,0,10,7,5);
        }

        public int  calcularDanio(Personaje objetivo){

            int danio = 6 + (int)(Math.random() * 5);
            return danio;
        }
    
}

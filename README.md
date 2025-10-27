Dragon Quest VIII           

Kevin Andres Rosero Romo – 2459554-2724

 Actualización y mejoras

Esta versión del código representa una actualización respecto a la versión inicial, con varias mejoras para asemejarse más al juego original y mejorar la sostenibilidad del código:

1. Mejoras en la estructura del código

Se reorganizó el proyecto en clases modulares (Personaje, Jugador, Enemigo, Juego) para cumplir mejor con principios de programación orientada a objetos.

Se añadieron enums (TipoHeroe, TipoEnemigo, Estado, TipoPersonaje) para manejar los tipos de personajes y estados de manera más clara y escalable.

Se implementaron métodos bien definidos para ataques, habilidades especiales y manejo de estados alterados (DORMIDO), lo que hace el juego más robusto y sostenible a largo plazo.

2. Héroes y habilidades

Héroes disponibles:

Heroe: Cura básica (12 HP, consume 3 MP)

Angelo: Cura avanzada (15 HP, consume 4 MP)

Jessica: Frizz (daño mágico 10–15, consume 4 MP)

Yangus: Golpe poderoso (+50% daño, 30% probabilidad de acertar)

Nota: Cada héroe solo puede usar su habilidad especial correspondiente.

3. Enemigos y mini jefe

Enemigos clásicos agregados: Slime, Dracky, PatyPunk, Spiked Hare.

Mini jefe: Terror Tabby

Tiene estadísticas superiores a los demás enemigos.

Posee un ataque especial (Sleep Attack) que puede dormir a los héroes durante 2 turnos con un 90% de probabilidad.

También inflige daño adicional (4–7 HP).

Esto lo convierte en un enemigo desafiante y fiel al concepto de mini jefe del juego original.

4. Mecánica del combate

Los turnos se alternan entre héroes y enemigos.

Los héroes pueden elegir entre ataque normal o habilidad especial.

Los enemigos pueden usar ataques normales o habilidades especiales de forma aleatoria.

Los personajes afectados por estados alterados (DORMIDO) pierden su turno hasta que el efecto desaparezca.

El combate termina cuando todos los héroes o todos los enemigos son derrotados.

5. Mejoras de estabilidad

Se validan entradas del usuario y objetivos de ataques.

Se controla el gasto de MP y curaciones para evitar errores de ejecución.

El juego maneja correctamente la resolución de estados alterados y los turnos de los efectos especiales.

Resumen

Esta versión actualizada:

Se acerca más al juego original en cuanto a personajes y enemigos.

Introduce un mini jefe con mecánica especial de sueño.

Optimiza la sostenibilidad y robustez del código para facilitar futuras ampliaciones.
package com.gbas.ms.server;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Teodorico on 31/07/2014.
 */
public class Util {
    static Logger logger = Logger.getLogger(Util.class);
    public static Random rd = new Random(System.currentTimeMillis());

    public static Map<String, Integer> letterToPos = new HashMap<String, Integer>();

    static {
        letterToPos.put("L", 3);
        letterToPos.put("R", 1);
        letterToPos.put("C", 2);
    }

    public static final String[] namesChicos = {
            "Adán",
            "Agustín",
            "Alberto",
            "Beto",
            "Alejandro",
            "Ale",
            "Alex",
            "Alfonso",
            "Alfredo",
            "Andrés",
            "Antonio",
            "Toni",
            "Tono",
            "Armando",
            "Arturo",
            "Tudi",
            "Benito",
            "Benjamín",
            "Bernardo",
            "Carlos",
            "Chacho",
            "Cacho",
            "César",
            "Claudio",
            "Clemente",
            "Tito",
            "Cristian",
            "Cristobal",
            "Daniel",
            "Dani",
            "David",
            "Diego",
            "Eduardo",
            "Edi",
            "Edu",
            "Emilio",
            "Enrique",
            "Quique",
            "Ernesto",
            "Esteban",
            "Federico",
            "Fede",
            "Felipe",
            "Fernando",
            "Nando",
            "Francisco",
            "Cisco",
            "Paco",
            "Pancho",
            "Gabriel",
            "Gabi",
            "Gerardo",
            "Gerar",
            "Germán",
            "Gilberto",
            "Gonzalo",
            "Gregorio",
            "Guillermo",
            "Guille",
            "Memo",
            "Gustavo",
            "Hernán",
            "Homero",
            "Horacio",
            "Hugo",
            "Ignacio",
            "Nacho",
            "Jacobo",
            "Jaime",
            "Javier",
            "Jerónimo",
            "Jesús",
            "Joaquín",
            "Jorge",
            "Jorge Luis",
            "José ",
            "Pepe",
            "José Eduardo",
            "José Emilio",
            "José Luis",
            "José María",
            "Juan",
            "Juancho",
            "Juan Carlos",
            "Juanca",
            "Juaca",
            "Julio",
            "Julio César",
            "Lorenzo",
            "Lucas",
            "Luis",
            "Lucho",
            "Luis Miguel",
            "Manuel",
            "Manolo",
            "Marco Antonio",
            "Marcos",
            "Mariano",
            "Mario",
            "Martín",
            "Mateo",
            "Miguel",
            "Miguel Ángel",
            "Nicolás",
            "Nico",
            "Octavio",
            "Óscar",
            "Pablo",
            "Patricio",
            "Pedro",
            "Rafael",
            "Rafa",
            "Ramiro",
            "Ramón",
            "Raúl",
            "Ricardo",
            "Riqui",
            "Roberto",
            "Beto",
            "Rodrigo",
            "Rodri",
            "Rubén",
            "Salvador",
            "Samuel",
            "Sancho",
            "Santiago",
            "Santi",
            "Sergio",
            "Teodoro",
            "Timoteo",
            "Timo",
            "Tomás",
            "Tomi",
            "Vicente",
            "Víctor"
    };

    public static final String[] namesChicas = {
            "Adela",
            "Adriana",
            "Alejandra",
            "Alicia",
            "Amalia",
            "Ana",
            "Ana Luisa",
            "Ana María",
            "Andrea",
            "Anita",
            "Ángela",
            "Antonia",
            "Toni",
            "Barbara",
            "Beatriz",
            "Berta",
            "Blanca",
            "Caridad",
            "Carla",
            "Carlota",
            "Carmen",
            "Carolina",
            "Caro",
            "Catalina",
            "Cata",
            "Cecilia",
            "Ceci",
            "Clara",
            "Claudia",
            "Concepción",
            "Concha",
            "Conchita",
            "Cristina",
            "Cris",
            "Tina",
            "Daniela",
            "Débora",
            "Diana",
            "Dolores",
            "Lola",
            "Dorotea",
            "Dora",
            "Elena",
            "Elisa",
            "Eloisa",
            "Elsa",
            "Elvira",
            "Emilia",
            "Emi",
            "Esperanza",
            "Estela",
            "Ester",
            "Eva",
            "Florencia",
            "Francisca",
            "Paca",
            "Paquita",
            "Gabriela",
            "Gabi",
            "Gloria",
            "Graciela",
            "Chela",
            "Guadalupe",
            "Lupe",
            "Guillermina",
            "Inés",
            "Irene",
            "Isabel",
            "Chabela",
            "Chavela",
            "Isa",
            "Isabela",
            "Josefina",
            "Pepita",
            "Juana",
            "Julia",
            "Laura",
            "Leonor",
            "Leticia",
            "Leti",
            "Lilia",
            "Lorena",
            "Lourdes",
            "Lucia",
            "Luisa",
            "Luz",
            "Magdalena",
            "Manuela",
            "Marcela",
            "Chela",
            "Margarita",
            "Rita",
            "María",
            "María del Carmen",
            "María Cristina",
            "María Elena",
            "María Eugenia",
            "María José",
            "Marijó",
            "María Luisa",
            "María Soledad",
            "María Teresa",
            "Maite",
            "Marite",
            "Mariana",
            "Maricarmen",
            "Marilu",
            "Marisol",
            "Marta",
            "Mercedes",
            "Meche",
            "Micaela",
            "Mónica",
            "Moni",
            "Natalia",
            "Nati",
            "Norma",
            "Olivia",
            "Patricia",
            "Pati",
            "Pilar",
            "Pili",
            "Ramona",
            "Raquel",
            "Rebeca",
            "Reina",
            "Rocio",
            "Rosa",
            "Rosi",
            "Rosita",
            "Rosalia",
            "Rosario",
            "Sara",
            "Saruca",
            "Silvia",
            "Sofia",
            "Soledad",
            "Sole",
            "Sonia",
            "Susana",
            "Susa",
            "Susanita",
            "Teresa",
            "Tere",
            "Verónica",
            "Vero",
            "Victoria",
            "Vicki",
            "Virginia",
            "Yolanda",
            "Yoli"
    };
    public static int[] imageChicas = {
            10, 12, 14, 22, 23, 24, 25, 26, 33, 38, 42, 43, 44, 45, 46, 81, 82, 83, 84, 85, 87, 88, 89, 90, 91, 96
    };
    public static int[] imageChicos = {
            9, 11, 13, 15, 16, 17, 18, 19, 20, 21, 27, 28, 29, 30, 31, 32, 34, 35, 36, 37, 39, 40, 41,
            47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 79,
            70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 86, 92, 93, 94, 95
    };

    public static void sleep(long timeMillis, String details) {
        logger.info(details);
        sleep(timeMillis);

    }

    public static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

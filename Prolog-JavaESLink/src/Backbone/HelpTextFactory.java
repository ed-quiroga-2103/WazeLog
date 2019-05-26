package Backbone;
//Factory that returns help messages
public class HelpTextFactory {

    public static String getHelp(int helpCode){

        switch (helpCode){
            //Consult help
            case 1: return "Para consultar una ruta, simplemente indique de forma clara la informacion que se le pide.\n" +
                    "Un ejemplo correcto puede ser 'Quiero ir a Cartago', mientras que un ejemplo" +
                    " no valido puede ser 'Voy a ir a comprar comida a Cartago'." +
                    "\nSe recomienda ser lo mas claro y conciso posible." +
                    "\nPara iniciar una consulta, envie un mensaje que diga 'Quiero iniciar una consulta' escribiendolo " +
                    "en la casilla directamente abajo de esta, o atraves del menu 'Opciones' en la opcion 'Realizar Consulta'" +
                    "\nPara mas informacion, consulte el manual de usuario.";
            //New node help
            case 2: return "Para crear una nueva ubicacion, presione en el menu de opciones. Seguidamente presione " +
                    "el boton 'Nueva ubicacion' y presione sobre el mapa donde desea colocar la nueva ubicacion. Una ventana" +
                    " se abrira para que coloque el nombre de la nueva ubicacion. Al finalizar presione el boton" +
                    " 'Finalizar' para colocar el nombre de la nueva ubicacion\n" +
                    "Para mas informacion, consulte el manual de usuario.";
            //New connection help
            case 3: return "Para conectar dos ubicaciones, presione en el menu de opciones. Seguidamente presione" +
                    " el boton 'Nueva conexion'. Al presionar el boton se abrira una ventana donde debera seleccionar" +
                    " las dos ubicaciones que desea conectar, la distancia entre estas, y una casilla donde se indica" +
                    " si la conexion va en ambos sentidos, o solamente en sentido origen-destino.\n" +
                    "Al finalizar presione el boton 'Conectar' para conectar las dos ubicaciones\n" +
                    "Para mas informacion, consulte el manual de usuario.";
            //General map help
            case 4: return  "En la pantalla se muesta el mapa registrado. Las conexiones verdes van en ambos sentidos mientras que las amarillas " +
                    "con verde solamente van en un sentido." +
                    " El segmento verde de la linea parte del origen y el amarillo concluye en el destino.\n"+
                    "Para mas informacion, consulte el manual de usuario.";
        }

        return null;
    }

}

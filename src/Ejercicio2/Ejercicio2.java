
package Ejercicio2;

import java.util.Scanner;


public class Ejercicio2 {
    
    Pila<Character> pila = new Pila<>();
//Determina el nivel de importancia de los operadores 
    public int Grado(char a) {
        switch (a) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
//Verifica si el elemento es un operador o no 
    public boolean esOperador(char a) {
        return (a == '+' || a == '-' || a == '*' || a == '/' || a == '^');
    }
//Metodo de transformacion de notacion infija a postfija
    public String InfijaToPostFija(String infija) {
        String postfija = "";
       /*Recorre cada elemento del string infija y se almacena en un char temporal
       para su evaluacion */
        
        for (int i = 0; i < infija.length(); i++) {
            char temp = infija.charAt(i);

            if (Character.isLetterOrDigit(temp)) {
                postfija += temp;
            } else if (temp == '(') {
                pila.push(temp);
            } else if (temp == ')') {
                /*se procede a despilar toda la pila hasta encontrar el otro 
                parentesis y a su vez almacenandolo en String postfija.
                */
                while (!pila.isEmpty() && pila.peek() != '(') {
                    postfija += pila.peek();
                    pila.pop();
                }
                pila.pop();
            } else if (esOperador(temp)) {
                /*Si el elemento a evaluar es menor o igual al ultimo elemento de la pila
                se procede a despilar todo y posteriormente se añade el elemento a la pila vacia*/
                while (!pila.isEmpty() && Grado(temp) <= Grado(pila.peek())) {
                    postfija += pila.peek();
                    pila.pop();
                }
                pila.push(temp);
            }
        }
/*Al terminar de recorrer todos los elementos , si la pila
 no esta vacia , se procede a vacear todo*/
        while (!pila.isEmpty()) {
            postfija += pila.peek();
            pila.pop();
        }

        return postfija;
    }
    
    public static void main(String[] args) {
    Ejercicio2 transformador = new Ejercicio2();
    Scanner entrada = new Scanner(System.in);
        String expresionInfija = "(x-y)/(z+w)-(z+y)^x";
       String postfija = transformador.InfijaToPostFija(expresionInfija);
        System.out.println("Expresión infija: " + expresionInfija);
        System.out.println("Expresión postfija: " + postfija);
      
        System.out.println("ola");
        System.out.println("probandofdsfsd");
    }
    
    
    
}

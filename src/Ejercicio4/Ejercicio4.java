
package Ejercicio4;

import java.util.Scanner;

public class Ejercicio4 {
    
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
        return (a == '+' || a == '-' ||
                a == '*' || a == '/' || a == '^');
    }

    //Metodo de transformacion de notacion infija a postfija
    public String InfijaToPostFija(String infija) {
        String postfija = "";
         /*Recorre cada elemento del string infija y se almacena en un char 
        temporal para su evaluacion */
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
                 /*Si el elemento a evaluar es menor o igual al ultimo elemento 
                de la pila se procede a despilar todo y posteriormente se añade 
                el elemento a la pila vacia*/
                while (!pila.isEmpty() && 
                        Grado(temp) <= Grado(pila.peek())) {
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
    //Metodo para resolver una notacion postfija
    public double evaluarPostFija(String postfija, 
            double x, double y, double z, double w) {    
    Pila<Double> evalPila = new Pila<>();
    for (int i = 0; i < postfija.length(); i++) {
        char temp = postfija.charAt(i);
        /* En caso sea una letra el caracter procede a ser 
        reemplazado por su valor correspondiente*/
        if (Character.isLetter(temp)) {          
            switch (temp) {
                case 'x':
                    evalPila.push(x);
                    break;
                case 'y':
                    evalPila.push(y);
                    break;
                case 'z':
                    evalPila.push(z);
                    break;
                case 'w':
                    evalPila.push(w);
                    break;
            }
            
        } 
        /*Si el elemento temp es un operador se procede a realizar la operacion
        y apila el resultado*/
            else if (esOperador(temp)) {
            
            double operand2 = evalPila.pop();
            double operand1 = evalPila.pop();
            double resultado = realizarOperacion(temp, operand1, operand2);
            evalPila.push(resultado);
        }
    }
    // la pila retorna el valor resultado final de la operacion
    return evalPila.pop();
}
    
/* A traves de los parametros dados , procede a realizar la 
    operacion correspondiente segun sea el operador y retorna 
    el resultado */  
private double realizarOperacion(char operador, double operand1, double operand2) {
    switch (operador) {
        case '+':
            return operand1 + operand2;
        case '-':
            return operand1 - operand2;
        case '*':
            return operand1 * operand2;
        case '/':
            return operand1 / operand2;
        case '^':
            return Math.pow(operand1, operand2);
        default:
            throw new IllegalArgumentException("Operador no válido: " + operador);
    }
}
    
    
    public static void main(String[] args) {
    Ejercicio4 transformador = new Ejercicio4();
    Scanner entrada = new Scanner(System.in);
        String expresionInfija = "x*y-(z+w)/(z+y)^x";
       String postfija = transformador.InfijaToPostFija(expresionInfija);
        System.out.println("Expresión infija: " + expresionInfija);
        System.out.println("Expresión postfija: " + postfija);
        System.out.println("Ingrese los valores de x y z w respectivamente");
        System.out.print("X : ");
        double x = entrada.nextDouble();
        System.out.println("");
        System.out.print("Y : ");
        double y = entrada.nextDouble();
        System.out.println("");
        System.out.print("Z : "); 
         double z = entrada.nextDouble();
        System.out.println("");
        System.out.print("W : "); 
        double w = entrada.nextDouble();
        System.out.println("");
       
    double resultado = transformador.evaluarPostFija(postfija, x, y, z, w);
    System.out.println("Resultado de la expresión: " + resultado);
   
    }
    
    
    
}

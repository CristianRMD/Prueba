
package Ejercicio2;

import java.util.EmptyStackException;


class Pila<E> {
    Nodo<E> tope;

    public Pila() {
        this.tope = null;
    }

    public void push(E dato) {
        Nodo<E> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E datoPop = tope.dato;
        tope = tope.siguiente;
        return datoPop;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return tope.dato;
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public void vaciarPila() {
        tope = null;
    }

    public void mostrarPila() {
        Nodo<E> temp = tope;
        System.out.print("Elementos de la pila: ");
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }
}
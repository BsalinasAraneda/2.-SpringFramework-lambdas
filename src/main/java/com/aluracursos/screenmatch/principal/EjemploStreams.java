package com.aluracursos.screenmatch.principal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class EjemploStreams {
    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria Fernanda", "Eric", "Genesys");
        List<Integer> numeros = Arrays.asList(5, 10, 20, 6, 89, 500, 3, 15, 21, 99, 3, 7, 83);

        System.out.println("*** Listado personas ***");
        System.out.println(nombres);

        // Utilizando Streams para ordenar la lista
        System.out.println("*** Listado ordenado ***");
        nombres.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("*** Cantidad de personas en la lista ***");
        System.out.println(nombres.stream().count());

        System.out.println("*** Lista no ordenada ***");
        // Metodo unordered() => Muestra el estado inicial de la lista al momento de creación
        nombres.stream().unordered().forEach(System.out::println);

        //######## Manipulando listado de numeros ################################ //
        //Ordenando lista de numeros
        System.out.println("*** Ordenando el listado de numeros ***");
        numeros.stream()
                .sorted()
                .distinct() // Muestra unicamente aquellos elementos que son unicos
                .forEach(System.out::println);

        System.out.println("*** Mostrando numeros mayores a 70 ***");
        numeros.stream()
                .filter(n -> n > 70)
                .forEach(System.out::println);

        System.out.println("*** Filtro entre 70 y 100");
        numeros.stream()
                .sorted()
                .filter(n -> n >= 70 && n <= 100)
                .forEach(System.out::println);

        System.out.println("*** Imprimiendo numeros pares ***");
        //Creando lista
        List<Integer> numerosPares =
                numeros.stream()
                        .sorted()
                        .filter(n -> n % 2 == 0)
                        .collect(toList());
        System.out.println(numerosPares);

        //### Agregando numeros pares a un conjunto set
        Set<Integer> integerSet =
                numeros.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toSet());
        System.out.println("*** conjunto Set() de numeros pares ***");
        System.out.println(integerSet);


        // Manipulando palabras

        List<String> palabras = Arrays.asList("Java", "Stream", "Operaciones", "Intermedias");
        palabras.stream()
                .forEach(n -> System.out.println(n + "tiene " + n.length() + " caracteres"));

        /**
         * Consideración importate al momento de implementar un stream()
         * Cuando finalizamos con .collect(Collectors.toList()); indicamos que es una lista de tipo mutable
         * por lo que si luego deseamos agregar nuevos elementos sera posible.
         * Pero si finalizamos o indicamos que es un .toList(), decimos que es una lista inmutable, por lo cual
         * al momento de añador elementos lanzara una excepción.
         * La opción que se utilice dependera de la circunstancia y requerimiento
         */

        System.out.println("*** Contando el largo de las palabras");
        System.out.println(palabras);
        List<Integer> largoPalabras = palabras.stream()
                .map(s -> s.length())
                .collect(toList());
        System.out.println(largoPalabras);


        System.out.println("** OTRA FORMA DE OBTENER EL LARGO DE LAS PALABRAS");

        List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> wordLengths =
                words.stream()
                        .map(String::length)
                        .collect(toList());

        System.out.println(wordLengths);


    }
}

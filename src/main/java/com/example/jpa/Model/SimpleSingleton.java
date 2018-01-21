package com.example.jpa.Model;

public class SimpleSingleton {
    //tworzymy intancje klasy przez static, czyli bedzie tlyko 1. A pozniej odwolujemy sie do tego
    //elementu. Caly czas zwracany jest ten sam obiekt SimpleSingleton
    private static SimpleSingleton simpleSingleton = new SimpleSingleton();
    public static SimpleSingleton getInstance(){
        return simpleSingleton;
    }

    //blokujemy konstruktor przez private, a do instancji tej klasy odwolujemy sie przez
    // simpleSingleton.getInstance()
    // singleton mozna wykorzystac np przy logowaniu gdzie uzytkownik przeskakuje miedzy
    // rozwnymi okienkami, aby nie przekazywac do okienek informacji odnosnie tego czy
    // user jest zalogowany. Ta informacja caly czas jest w danej instacji
    //podobnie do łączenia sie z bazą, aby nie łączyć sięprzy kazdym zapytaniu, mozna
    // skorzystac z singletona
    int counter;
    private SimpleSingleton(){
        counter = 0;
    }
    public void incrementCounter(){
        counter++;
    }
    public int getCounter(){
        return counter;
    }
}

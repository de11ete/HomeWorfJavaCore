package lesson4;

import java.util.*;

class HomeWork3 {

    public static void main(String[] args) {

        Animals();
        TelephoneBook();
    }

    private static void Animals() {
        Map<String, Integer> hm = new HashMap<>();
        String[] animals = {
                "cat", "dog", "parrot",
                "hamster", "mouse", "rat",
                "pig", "horse", "parrot", "hamster"};

        for (int i = 0; i < animals.length; i++) {
            if (hm.containsKey(animals[i]))
                hm.put(animals[i], hm.get(animals[i]) + 1);
            else
                hm.put(animals[i], 1);
        }
        System.out.println(hm);
    }

    private static void TelephoneBook() {
        Directory directory = new Directory();

        directory.add("Lobanov", "8888111111");
        directory.add("Lobanov", "899922222");
        directory.add("Sinicin", "877733333");
        directory.add("Sinicin", "866644444");
        directory.add("Kurnik", "855533333");
        directory.add("Smirkin", "844477777");

        System.out.print(directory.get("Lobanov"));
        System.out.print(directory.get("Sinicin"));
        System.out.print(directory.get("Kurnik"));
        System.out.print(directory.get("Smirkin"));
    }
}

class Directory {
    private Map<String, List<String>> directory_hm = new HashMap<>();
    private List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (directory_hm.containsKey(surname)) {
            phone_number_list = directory_hm.get(surname);
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return directory_hm.get(surname);
    }

}
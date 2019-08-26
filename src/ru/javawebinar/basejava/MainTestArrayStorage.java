package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1");
        Resume resume2 = new Resume("uuid2");
        Resume resume3 = new Resume("uuid3");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);

        printAll();
        System.out.println("Get r1: " + ARRAY_STORAGE.get(resume1.toString()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        ARRAY_STORAGE.update("uuid1", "uuid1-new");    // new uuid for existing resume
        System.out.println("Get r1: " + ARRAY_STORAGE.get(resume1.toString()));
        printAll();

        ARRAY_STORAGE.update("uuid1-new", "uuid3");   //  uuid is not unique
        ARRAY_STORAGE.update("uuid4", "new");         //  resume in not in strorage

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(resume1.toString());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}

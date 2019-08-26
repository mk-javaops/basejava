package ru.javawebinar.basejava.model;
/**
 * Initial resume class
 */
public class Resume {
    private String uuid;        // Unique identifier

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}

package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_RESUME_AMOUNT = 10000;
    private Resume[] storage = new Resume[MAX_RESUME_AMOUNT];
    private int resumeAmount = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeAmount, null);
        resumeAmount = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index == -1) {
            storage[resumeAmount] = resume;
            resumeAmount++;
        } else {
            System.out.println("ERROR! save uuid is`t unique.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            return storage[index];
        }
        System.out.println("ERROR! get uuid does not exist");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, resumeAmount - 1 - index);
            storage[resumeAmount - 1] = null;
            resumeAmount--;
        } else {
            System.out.println("ERROR! delete uuid doesn`t exist");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR! update uuid doesn`t exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[resumeAmount];

        System.arraycopy(storage, 0, resumes, 0, resumeAmount);
        return resumes;
    }

    public int size() {
        return resumeAmount;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < resumeAmount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

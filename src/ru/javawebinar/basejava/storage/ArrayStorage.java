package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int MAX_RESUME_AMOUNT = 10000;
    private Resume[] storage = new Resume[MAX_RESUME_AMOUNT];
    private final int UUID_NOT_FOUND = -1;
    private int resumeAmount = 0;

    public void clear() {
        Arrays.fill(storage, 0, resumeAmount, null);
        resumeAmount = 0;
    }

    public void save(Resume resume) {
        if (resume.toString() == null) {
            System.out.println("Save ERROR! Resume uuid can`t be empty");
            return;
        }

        if (resumeAmount == MAX_RESUME_AMOUNT) {
            System.out.println("Save ERROR! Resume storage is overloaded");
        } else if (uuidFinder(resume.toString()) != UUID_NOT_FOUND) {
            System.out.println("Save ERROR! Resume is not unique");
        } else {
            storage[resumeAmount] = resume;
            resumeAmount++;
        }
    }

    public void update(String uuid, String uuidNew) {
        if (uuid == null || uuidNew == null) {
            System.out.println("Update ERROR! Resume uuid or uuidNew can`t be empty");
            return;
        }

        int resumeIndex = uuidFinder(uuid);
        if (resumeIndex == UUID_NOT_FOUND) {
            System.out.println("Update ERROR! Resume is not exist.");
        }  else if (uuidFinder(uuidNew) != UUID_NOT_FOUND) {
            System.out.println("Update ERROR! Resume update uuid is not unique.");
        } else {
            storage[resumeIndex].setUuid(uuidNew);
        }
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            System.out.println("Get ERROR! Resume uuid can`t be empty");
            return null;
        }

        int resumeIndex = uuidFinder(uuid);
        if (resumeIndex == UUID_NOT_FOUND) {
            System.out.println("Get ERROR! Resume is not exist.");
            return null;
        } else {
            return storage[resumeIndex];
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            System.out.println("Delete ERROR! Resume uuid can`t be empty");
            return;
        }
        int resumeIndex = uuidFinder(uuid);
        if (resumeIndex == UUID_NOT_FOUND) {
            System.out.println("Delete ERROR! Nothing to delete, resume uuid not found.");
        } else {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, resumeAmount - 1 - resumeIndex);
            storage[resumeAmount] = null;
            resumeAmount--;
        }
    }

    private int uuidFinder(String uuid) {
        for (int i = 0; i < resumeAmount; i++) {
            if (uuid.equals(storage[i].toString())) {
                return i;
            }
        }
        return UUID_NOT_FOUND;
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
}

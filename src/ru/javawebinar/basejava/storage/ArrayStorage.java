package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int MAX_RESUME_AMOUNT = 10000;
    private Resume[] storage = new Resume[MAX_RESUME_AMOUNT];
    private int resumeAmount = 0;

    public int getResumeAmount() {
        return resumeAmount;
    }

    public void clear() {
        Arrays.fill(storage, 0, resumeAmount, null);
        resumeAmount = 0;
    }

    public void save(Resume resume) {
        boolean validity = true;
        String uuid = resume.getUuid();

        if (uuid == null) {
            System.out.println(" ERROR! resume uuid can`t be empty");
            validity = false;
        } else if (uuidFinder(uuid) != -1) {
            System.out.println(" ERROR! Resume save uuid is not unique.");
            validity = false;
        } else if (resumeAmount == MAX_RESUME_AMOUNT) {
            System.out.println(" ERROR! Resume storage is overloaded");
            validity = false;
        }
        if (validity) {
            storage[resumeAmount] = resume;
            resumeAmount++;
        }
    }

    public void update(String uuid, Resume resume) {
        boolean validity = true;
        int resumeIndex = uuidFinder(uuid);
        String uuidNew = resume.getUuid();

        if (uuid == null) {
            System.out.println(" ERROR! uuid of resume can`t be empty");
            validity = false;
        } else if (uuidFinder(uuid) == -1) {
            System.out.println(" Error! Nothing to get. Updated resume does`t exist");
            validity = false;
        } else if (uuidFinder(uuidNew) != -1) {
            System.out.println(" ERROR! Updated resume uuid is not unique.");
            validity = false;
        }
        if (validity) {
            storage[resumeIndex] = resume;
        }
    }

    public Resume get(String uuid) {
        boolean validity = true;

        if (uuid == null) {
            System.out.println(" ERROR! resume uuid can`t be empty");
            validity = false;
        } else if (uuidFinder(uuid) == -1) {
            System.out.println(" Error! Nothing to get. Resume uuid not found");
            validity = false;
        }
        if (validity) {
            return storage[uuidFinder(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        boolean validity = true;
        int resumeIndex = uuidFinder(uuid);

        if (uuid == null) {
            System.out.println(" ERROR! resume uuid can`t be empty");
            validity = false;
        } else if (resumeIndex == -1) {
            System.out.println(" Error! Nothing to delete. Resume uuid not found");
            validity = false;
        }
        if (validity) {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, resumeAmount - 1 - resumeIndex);
            storage[resumeAmount] = null;
            resumeAmount--;
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

    public int uuidFinder(String uuid) {
        for (int i = 0; i < resumeAmount; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

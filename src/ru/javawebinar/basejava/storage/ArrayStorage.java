package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Request;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int MAX_RESUME_AMOUNT = 10000;
    private Resume[] storage = new Resume[MAX_RESUME_AMOUNT];
    public static final int UUID_NOT_FOUND = -1;
    private int resumeAmount = 0;
    private Request request;

    public int getResumeAmount() {
        return resumeAmount;
    }

    public void clear() {
        Arrays.fill(storage, 0, resumeAmount, null);
        resumeAmount = 0;
    }

    public void save(Resume resume) {
        request = new Request(resume.toString(), "save");
        if (request.isValid(this)) {
            storage[resumeAmount] = resume;
            resumeAmount++;
        }
    }

    public void update(String uuid, String uuidNew) {
        boolean validity1;
        boolean validity2;

        request = new Request(uuid, "updateUUID1");
        validity1 = request.isValid(this);
        request = new Request(uuidNew, "updateUUID2");
        validity2 = request.isValid(this);

        if (validity1 && validity2) {
            storage[uuidFinder(uuid)].setUuid(uuidNew);
        }
    }

    public Resume get(String uuid) {
        request = new Request(uuid, "get");
        if (request.isValid(this)) {
            return storage[uuidFinder(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        request = new Request(uuid, "delete");
        if (request.isValid(this)) {
            int resumeIndex = uuidFinder(uuid);
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
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return UUID_NOT_FOUND;
    }
}

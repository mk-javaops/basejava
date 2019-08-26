package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.ArrayStorage;

import static ru.javawebinar.basejava.storage.ArrayStorage.MAX_RESUME_AMOUNT;
import static ru.javawebinar.basejava.storage.ArrayStorage.UUID_NOT_FOUND;

public class Request {
    private String uuid;
    private String method;

    public Request(String uuid, String method) {
        this.uuid = uuid;
        this.method = method;
    }

    public boolean isValid(ArrayStorage arrayStorage) {
        int resumeIndex = arrayStorage.uuidFinder(uuid);
        boolean validity = true;

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace[2].getMethodName();

        if (uuid == null) {
            System.out.println(methodName + " ERROR! For command \"" + methodName + "\" resume uuid can`t be empty");
            validity = false;
        }
        switch (method) {
            case "save":
                if (resumeIndex != UUID_NOT_FOUND) {
                    System.out.println(methodName + " ERROR! Resume save uuid is not unique.");
                    validity = false;
                } else if (arrayStorage.getResumeAmount() == MAX_RESUME_AMOUNT) {
                    System.out.println(methodName + " ERROR! Resume storage is overloaded");
                    validity = false;
                }
                break;
            case "updateUUID1":
            case "get":
            case "delete":
                if (resumeIndex == UUID_NOT_FOUND) {
                    System.out.println(methodName + " Error! Nothing to \"" + methodName + "\" Resume uuid not found");
                    validity = false;
                }
                break;
            case "updateUUID2":
                if (resumeIndex != UUID_NOT_FOUND) {
                    System.out.println(methodName + " ERROR! Resume uuid is not unique.");
                    validity = false;
                }
                break;
        }
        return validity;
    }
}

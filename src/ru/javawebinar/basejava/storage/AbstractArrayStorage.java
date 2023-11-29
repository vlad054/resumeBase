package ru.javawebinar.basejava.storage;

import exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected boolean isExist(Integer o) {
        return o >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected List<Resume> getList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected abstract Integer getSearchKey(String uuid);

    protected void doSave(Resume r, Integer i) {

        if (isOverflow()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doSaveArr(r, i);
            size++;
        }
    }

    protected void doDelete(Integer i) {
        doDeleteArr(i);
        storage[size - 1] = null;
        size--;
    }

    protected boolean isOverflow() {
        return size >= STORAGE_LIMIT;
    }

    @Override
    protected Resume doGet(Integer i) {
        return storage[(int) i];
    }

    @Override
    protected void doUpdate(Integer o, Resume r) {
        storage[(int) o] = r;
    }

    protected abstract void doSaveArr(Resume r, int i);

    protected abstract void doDeleteArr(int i);
}

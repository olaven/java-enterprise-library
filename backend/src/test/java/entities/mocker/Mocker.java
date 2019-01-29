package entities.mocker;

import entities.Author;
import entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Returns valid mock-objects
 * for the tests
 */
public abstract class Mocker<T> {

    public abstract T getOne();

    public List<T> getMany(int count) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getOne());
        }
        return list;
    }


    protected<E> E getRandomFrom(E[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);

        return array[index];
    }


}

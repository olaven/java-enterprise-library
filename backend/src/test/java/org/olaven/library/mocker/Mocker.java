package org.olaven.library.mocker;

import java.util.ArrayList;
import java.util.List;

public abstract class Mocker<T> {

    public abstract T getOne();

    public List<T> getMany(int n) {

        List<T> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(getOne());
        }

        return list;
    }
}

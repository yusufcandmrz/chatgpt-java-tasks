package yusufcandmrz.genericrepositorysystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> store = new HashMap<>();

    @Override
    public void save(T entity) {
    }

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void deleteById(ID id) {;
    }
}

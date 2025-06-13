package com.example.ch2labs.labs07;

import com.example.ch2labs.labs07.domain.Model;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {

    private final Map<Long, Model> store = new HashMap<>();
    private Long sequence = 0L;

    // Create
    public Model save(Model model) {
        model.setId(++sequence);
        store.put(model.getId(), model);
        return model;
    }
    public Optional<Model> findById(Long id) {return Optional.ofNullable(store.get(id));}

    public List<Model> findAll() {return new ArrayList<>(store.values());}

    public void delete(Long id) {store.remove(id);}
}

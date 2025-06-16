package com.example.ch2labs.labs08;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReserveRepository {
    private final Map<Long, Model> store = new HashMap<>();
    private Long sequence = 0L;

    public Model save(Model model) {
        model.setModelId(++sequence);
        store.put(model.getModelId(), model);
        return model;
    }
}

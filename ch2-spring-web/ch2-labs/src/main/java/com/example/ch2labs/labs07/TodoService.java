package com.example.ch2labs.labs07;

import com.example.ch2labs.labs07.DTO.RequestCreateDTO;
import com.example.ch2labs.labs07.DTO.RequestUpdateDTO;
import com.example.ch2labs.labs07.DTO.ResponseDTO;
import com.example.ch2labs.labs07.domain.Model;
import com.example.ch2labs.labs07.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public ResponseDTO getTodo(Long id){
        Model model = todoRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        return toResponseDTO(model);
    }

    public List<ResponseDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(model -> toResponseDTO(model))
                .collect(Collectors.toList());
    }

    public ResponseDTO toResponseDTO(Model model) {
        return new ResponseDTO(model.getId(), model.getTitle(), model.isCompleted());
    }

    public ResponseDTO createModel(RequestCreateDTO requestCreateDTO) {
        Model model = new Model(null, requestCreateDTO.getTitle(), requestCreateDTO.getCompleted());
        Model savedModel = todoRepository.save(model);
        return toResponseDTO(savedModel);
    }

    public ResponseDTO updateModel(Long id, RequestUpdateDTO requestUpdateDTO) {
        Model model = todoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Model not found"));
        model.setTitle(requestUpdateDTO.getTitle());
        model.setCompleted(requestUpdateDTO.isCompleted());
        todoRepository.save(model);
        return toResponseDTO(model);
    }
    public void deleteModel(Long id) {
        todoRepository.delete(id);
    }
}

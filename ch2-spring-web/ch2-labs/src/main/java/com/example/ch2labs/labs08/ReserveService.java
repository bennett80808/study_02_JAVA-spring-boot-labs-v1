package com.example.ch2labs.labs08;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository repository;

    public SuccessResponseDTO createModel(RequestDTO requestDTO) {
        Model model = new Model(null, requestDTO.getUserId(),
                requestDTO.getStartTime(),requestDTO.getEndTime());
        Model savedModel = repository.save(model);
        return toResponse(savedModel);
    }

    public SuccessResponseDTO toResponse(Model model) {
        String message = model.getUserId() + "님의 예약이 완료되었습니다.";
        return new SuccessResponseDTO(message);
    }
}

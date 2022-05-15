package com.example.perspective.service.discussion.perspective;

import com.example.perspective.model.DTO.PerspectiveDTO;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;

public interface PerspectiveService {

    PerspectiveDTO save(PerspectiveDTO perspectiveDTO) throws InvalidDataException, DuplicateNameException;

    PerspectiveDTO update(PerspectiveDTO perspectiveDTO) throws InvalidDataException, DuplicateNameException;
}

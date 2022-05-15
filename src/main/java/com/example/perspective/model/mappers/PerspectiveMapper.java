package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.PerspectiveDTO;
import com.example.perspective.model.Perspective;

public class PerspectiveMapper implements Mapper<Perspective, PerspectiveDTO> {

    /**
     * Singleton instance of the PerspectiveMapper class.
     */
    private static PerspectiveMapper perspectiveMapper = null;

    /**
     * Constructor.
     */
    private PerspectiveMapper() {

    }

    /**
     * Retrieves the single instance of the PerspectiveMapper class.
     * @return the instance of the PerspectiveMapper class.
     */
    public static PerspectiveMapper getInstance() {
        if (perspectiveMapper == null) {
            perspectiveMapper = new PerspectiveMapper();
        }
        return perspectiveMapper;
    }

    @Override
    public Perspective convertFromDTO(PerspectiveDTO perspectiveDTO) {
        return null;
    }

    @Override
    public PerspectiveDTO convertToDTO(Perspective perspective) {
        return PerspectiveDTO.builder()
                .belief(perspective.getBelief())
                .topicDTO(TopicMapper.getInstance().convertToDTO(perspective.getTopic()))
                .userDTO(UserMapper.getInstance().convertToDTO(perspective.getUser()))
                .notes(perspective.getNotes())
                .build();
    }
}

package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Singleton class used for mapping an Resource to a ResourceDTO and vice-versa.
 */
public class ResourceMapper implements Mapper<Resource, ResourceDTO> {

    /**
     * Singleton instance of the ResourceMapper class.
     */
    private static ResourceMapper resourceMapper = null;

    /**
     * Constructor.
     */
    private ResourceMapper() {

    }

    /**
     * Retrieves the single instance of the ResourceMapper class.
     * @return the instance of the ResourceMapper class.
     */
    public static ResourceMapper getInstance() {
        if (resourceMapper == null) {
            resourceMapper = new ResourceMapper();
        }
        return resourceMapper;
    }

    @Override
    public Resource convertFromDTO(ResourceDTO resourceDTO) throws IOException {
        String resourceName = StringUtils.cleanPath(Objects.requireNonNull(resourceDTO.getFile().getOriginalFilename()));
        return Resource.builder()
                .name(resourceName)
                .expert(ExpertMapper.getInstance().convertFromDTO(resourceDTO.getExpertDTO()))
                .topic(TopicMapper.getInstance().convertFromDTO(resourceDTO.getTopicDTO()))
                .mimeType(resourceDTO.getFile().getContentType())
                .data(resourceDTO.getFile().getBytes())
                .build();
    }

    @Override
    public ResourceDTO convertToDTO(Resource resource) {
        return ResourceDTO.builder()
                .name(resource.getName())
                .expertDTO(ExpertMapper.getInstance().convertToDTO(resource.getExpert()))
                .topicDTO(TopicMapper.getInstance().convertToDTO(resource.getTopic()))
                .mimeType(resource.getMimeType())
                .data(resource.getData())
                .build();
    }
}

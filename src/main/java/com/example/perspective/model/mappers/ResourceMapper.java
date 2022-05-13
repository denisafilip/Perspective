package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.Resource;

/**
 * Singleton class used for mapping an Resource to an ResourceDTO and vice-versa.
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
    public Resource convertFromDTO(ResourceDTO resourceDTO) {
        return null;
    }

    @Override
    public ResourceDTO convertToDTO(Resource resource) {
        return ResourceDTO.builder()
                .name(resource.getName())
                .expert(resource.getExpert())
                .topic(resource.getTopic())
                .type(resource.getType())
                .build();
    }
}

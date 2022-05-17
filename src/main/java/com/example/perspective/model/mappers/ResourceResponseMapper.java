package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.ResourceResponseDTO;
import com.example.perspective.model.Resource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

public class ResourceResponseMapper implements Mapper<Resource, ResourceResponseDTO> {

    /**
     * Singleton instance of the ResourceResponseMapper class.
     */
    private static ResourceResponseMapper resourceMapper = null;

    /**
     * Constructor.
     */
    private ResourceResponseMapper() {

    }

    /**
     * Retrieves the single instance of the ResourceResponseMapper class.
     * @return the instance of the ResourceResponseMapper class.
     */
    public static ResourceResponseMapper getInstance() {
        if (resourceMapper == null) {
            resourceMapper = new ResourceResponseMapper();
        }
        return resourceMapper;
    }


    @Override
    public Resource convertFromDTO(ResourceResponseDTO resourceResponseDTO) {
        return null;
    }

    @Override
    public ResourceResponseDTO convertToDTO(Resource resource) {
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/resource/")
                .path(Integer.toString(resource.getIdResource()))
                .toUriString();

        return ResourceResponseDTO.builder()
                .name(resource.getName())
                .url(fileDownloadUri)
                .size(resource.getData().length)
                .type(resource.getMimeType())
                .subjectDTO(SubjectMapper.getInstance().convertToDTO(resource.getTopic().getSubject()))
                .build();
    }
}

package com.example.perspective.service.discussion.resource;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.DTO.ResourceResponseDTO;
import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Resource;
import com.example.perspective.model.Topic;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ResourceService {

    /**
     * Retrieves all resources from the database.
     * @return Retrieved list of resources.
     */
    List<ResourceResponseDTO> findAll();

    /**
     * Retrieves a resource from the database by its name.
     * @param name Name of the resource we search for.
     * @return The found resource.
     */
    Resource findByName(String name);

    /**
     * Stores a resource file added by an expert user to the database.
     * @param topicName Name of hte topic for which the resource was added.
     * @param expertDTO Expert which added the resource.
     * @param file Resource that was added.
     * @return The newly saved resource.
     */
    ResourceResponseDTO save(String topicName, ExpertDTO expertDTO, MultipartFile file) throws InvalidDataException, DuplicateNameException, IOException;

    /**
     * Retrieves a resource file from the database by its name.
     * @param id Id of the resource to be retrieved.
     * @return The retrieved resource.
     */
    Resource getResource(Integer id);

    List<ResourceResponseDTO> findAllByExpertEmail(String expertEmail) throws InvalidDataException;

    List<ResourceResponseDTO> findAllByTopicName(String topicName) throws InvalidDataException;

}

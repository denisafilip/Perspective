package com.example.perspective.service.discussion.resource;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.DTO.ResourceResponseDTO;
import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Expert;
import com.example.perspective.model.Resource;
import com.example.perspective.model.Topic;
import com.example.perspective.model.mappers.ResourceMapper;
import com.example.perspective.model.mappers.ResourceResponseMapper;
import com.example.perspective.repository.ResourceRepository;
import com.example.perspective.service.account.expert.ExpertUserService;
import com.example.perspective.service.discussion.topic.TopicService;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class.getName());

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ExpertUserService expertUserService;

    @Override
    public List<ResourceResponseDTO> findAll() {
        logger.info("Retrieve all stored resources from the database.");
        return resourceRepository.findAll().stream()
                .map(ResourceResponseMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Resource findByName(String name) {
        return resourceRepository.findByName(name).orElse(null);
    }

    @Override
    public ResourceResponseDTO save(String topicName, ExpertDTO expertDTO, MultipartFile file) throws InvalidDataException, IOException {
        logger.info("Saving resource to database...");
        Topic t = topicService.findByName(topicName);

        if (t == null) {
            logger.error("Could not find any topic with the name {}!", topicName);
            throw new InvalidDataException("Could not find any topic with the name " + topicName + "!");
        }

        Expert e = expertUserService.findByEmail(expertDTO.getEmail());

        if (e == null) {
            logger.error("Could not find any expert user with the email {}!", expertDTO.getEmail());
            throw new InvalidDataException("Could not find any expert user with the email " + expertDTO.getEmail() + "!");
        }

        String resourceName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Resource resource = Resource.builder()
                .name(resourceName)
                .mimeType(file.getContentType())
                .data(file.getBytes())
                .expert(e)
                .topic(t)
                .build();

        Resource savedResource = resourceRepository.save(resource);

        return ResourceResponseMapper.getInstance().convertToDTO(savedResource);
    }

    @Override
    public Resource getResource(Integer id) {
        Resource resource = resourceRepository.findById(id).orElse(null);
        if (resource == null) {
            logger.warn("No resource with id {} was found!", id);
            return null;
        }
        return resource;
    }

    @Override
    public List<ResourceResponseDTO> findAllByExpertEmail(String expertEmail) throws InvalidDataException {
        Expert e = expertUserService.findByEmail(expertEmail);

        if (e == null) {
            logger.error("Could not find any expert user with the email {}!", expertEmail);
            throw new InvalidDataException("Could not find any expert user with the email " + expertEmail + "!");
        }

        return resourceRepository.findAllByExpert(e).stream()
                .map(ResourceResponseMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }
}

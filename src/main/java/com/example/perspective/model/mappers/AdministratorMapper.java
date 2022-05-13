package com.example.perspective.model.mappers;

import com.example.perspective.model.Administrator;
import com.example.perspective.model.DTO.AdministratorDTO;

/**
 * Singleton class used for mapping an Administrator to an AdministratorDTO and vice-versa.
 */
public class AdministratorMapper implements Mapper<Administrator, AdministratorDTO> {

    /**
     * Singleton instance of the AdministratorMapper class.
     */
    private static AdministratorMapper administratorMapper = null;

    /**
     * Constructor.
     */
    private AdministratorMapper() {

    }

    /**
     * Retrieves the single instance of the AdministratorMapper class.
     * @return the instance of the AdministratorMapper class.
     */
    public static AdministratorMapper getInstance() {
        if (administratorMapper == null) {
            administratorMapper = new AdministratorMapper();
        }
        return administratorMapper;
    }

    @Override
    public Administrator convertFromDTO(AdministratorDTO administratorDTO) {
        return null;
    }

    @Override
    public AdministratorDTO convertToDTO(Administrator administrator) {
        return AdministratorDTO.AdministratorDTOBuilder()
                .username(administrator.getUsername())
                .firstName(administrator.getFirstName())
                .lastName(administrator.getLastName())
                .email(administrator.getEmail())
                .password(administrator.getPassword())
                .build();
    }
}

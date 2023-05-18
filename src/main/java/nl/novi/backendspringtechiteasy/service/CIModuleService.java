package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.CIModuleDto;
import nl.novi.backendspringtechiteasy.model.CIModule;
import nl.novi.backendspringtechiteasy.repository.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepos;

    public CIModuleService(CIModuleRepository ciModuleRepos) {
        this.ciModuleRepos = ciModuleRepos;
    }

    public Long createCIModule(CIModuleDto ciModuleDto) {
        CIModule ciModule = new CIModule();
        ciModuleRepos.save(transferCIModuleDtoToCIModule(ciModuleDto, ciModule));
        return ciModule.getId();
    }

    public ArrayList<CIModuleDto> getAllCIModules() {
        ArrayList<CIModuleDto> ciModuleDtos = new ArrayList<>();
        Iterable<CIModule> ciModules = ciModuleRepos.findAll();
        for (CIModule ciModule : ciModules) {
            ciModuleDtos.add(transferCIModuleToCIModuleDto(ciModule));
        }
        return ciModuleDtos;
    }



    private CIModuleDto transferCIModuleToCIModuleDto(CIModule ciModule) {
        CIModuleDto ciModuleDto = new CIModuleDto();
        ciModuleDto.id = ciModule.getId();
        ciModuleDto.name = ciModule.getName();
        ciModuleDto.type = ciModule.getType();
        ciModuleDto.price = ciModule.getPrice();
        return ciModuleDto;
    }

    private CIModule transferCIModuleDtoToCIModule(CIModuleDto ciModuleDto, CIModule ciModule) {
        ciModule.setName(ciModuleDto.name);
        ciModule.setType(ciModuleDto.type);
        ciModule.setPrice(ciModuleDto.price);
        return ciModule;
    }
}

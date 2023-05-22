package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.CIModuleDto;
import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
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

    public CIModuleDto getCIModule(Long id) {
        CIModule ciModule = ciModuleRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("CI Module was not found in the database"));
        return transferCIModuleToCIModuleDto(ciModule);
    }

    public void updateCIModule(CIModuleDto ciModuleDto, Long id) {
        CIModule ciModule = ciModuleRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("CI Module was not found in the database"));
        if (ciModule != null) {
            ciModuleRepos.save(transferCIModuleDtoToCIModule(ciModuleDto, ciModule));
        }
    }

    public void deleteCIModule(Long id) {
        ciModuleRepos.deleteById(id);
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

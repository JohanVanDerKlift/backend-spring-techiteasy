package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.RemoteControllerDto;
import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import nl.novi.backendspringtechiteasy.model.RemoteController;
import nl.novi.backendspringtechiteasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepos;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepos) {
        this.remoteControllerRepos = remoteControllerRepos;
    }

    public Long createRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController remoteController = new RemoteController();
        remoteControllerRepos.save(transferRemoteControllerDtoToRemoteController(remoteControllerDto, remoteController));
        return remoteController.getId();
    }

    public ArrayList<RemoteControllerDto> getAllRemoteControllers() {
        Iterable<RemoteController> remoteControllers = remoteControllerRepos.findAll();
        ArrayList<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();
        for (RemoteController r : remoteControllers) {
            RemoteControllerDto remoteControllerDto = transferRemoteControllerToRemoteControllerDto(r);
            remoteControllerDtos.add(remoteControllerDto);
        }
        return remoteControllerDtos;
    }

    public RemoteControllerDto getRemoteController(Long id) {
        RemoteController remoteController = remoteControllerRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller was not found in the database"));
        return transferRemoteControllerToRemoteControllerDto(remoteController);
    }

    public void updateRemoteController(RemoteControllerDto remoteControllerDto, Long id) {
        RemoteController remoteController = remoteControllerRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller was not found in the database"));
        if (remoteController != null) {            ;
            remoteControllerRepos.save(transferRemoteControllerDtoToRemoteController(remoteControllerDto, remoteController));
        }
    }

    public void deleteRemoteController(Long id) {
        remoteControllerRepos.deleteById(id);
    }

    private RemoteControllerDto transferRemoteControllerToRemoteControllerDto(RemoteController r) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        remoteControllerDto.batteryType = r.getBatteryType();
        remoteControllerDto.brand = r.getBrand();
        remoteControllerDto.compatibleWith = r.getCompatibleWith();
        remoteControllerDto.name = r.getName();
        remoteControllerDto.id = r.getId();
        remoteControllerDto.originalStock = r.getOriginalStock();
        remoteControllerDto.price = r.getPrice();
        return remoteControllerDto;
    }

    private RemoteController transferRemoteControllerDtoToRemoteController(RemoteControllerDto remoteControllerDto, RemoteController r) {
        r.setBatteryType(remoteControllerDto.batteryType);
        r.setBrand(remoteControllerDto.brand);
        r.setName(remoteControllerDto.name);
        r.setCompatibleWith(remoteControllerDto.compatibleWith);
        r.setPrice(remoteControllerDto.price);
        r.setOriginalStock(remoteControllerDto.originalStock);
        return r;
    }
}

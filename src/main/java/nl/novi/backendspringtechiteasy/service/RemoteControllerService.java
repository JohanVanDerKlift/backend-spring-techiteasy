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
        remoteController.setBatteryType(remoteControllerDto.batteryType);
        remoteController.setBrand(remoteControllerDto.brand);
        remoteController.setName(remoteControllerDto.name);
        remoteController.setCompatibleWith(remoteControllerDto.compatibleWith);
        remoteController.setPrice(remoteControllerDto.price);
        remoteController.setOriginalStock(remoteControllerDto.originalStock);
        remoteControllerRepos.save(remoteController);
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
}

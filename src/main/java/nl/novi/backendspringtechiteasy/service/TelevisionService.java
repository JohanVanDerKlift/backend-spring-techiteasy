package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.TelevisionDto;
import nl.novi.backendspringtechiteasy.dto.WallBracketDto;
import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import nl.novi.backendspringtechiteasy.model.RemoteController;
import nl.novi.backendspringtechiteasy.model.Television;
import nl.novi.backendspringtechiteasy.model.WallBracket;
import nl.novi.backendspringtechiteasy.repository.RemoteControllerRepository;
import nl.novi.backendspringtechiteasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final WallBracketService wallBracketService;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, WallBracketService wallBracketService) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.wallBracketService = wallBracketService;
    }

    //    public TelevisionDto assignRemoteControllerToTelevision(Long id, Long remoteControllerId) throws RecordNotFoundException {
//        Optional<Television> optionalTelevision= televisionRepository.findById(id);
//        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
//        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
//            Television television = optionalTelevision.get();
//            RemoteController remoteController = optionalRemoteController.get();
//            television.setRemoteController(remoteController);
//            Television updatedTelevision = televisionRepository.save(television);
//            return transferTelevisionToTelevisionDto(updatedTelevision);
//        } else {
//            throw new RecordNotFoundException("Record not found");
//        }
//    }

    public TelevisionDto assignRemoteControllerToTelevision(Long televisionId, Long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
        if (optionalTelevision.isEmpty() && optionalRemoteController.isEmpty()) {
            throw new RecordNotFoundException("Records not found");
        }
        Television television = optionalTelevision.get();
        RemoteController remoteController = optionalRemoteController.get();
        television.setRemoteController(remoteController);
        televisionRepository.save(television);
        return transferTelevisionToTelevisionDto(television);
    }

    public List<TelevisionDto> getTelevisions() {
        Iterable<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();
        for (Television t : televisions) {
            TelevisionDto televisionDto = transferTelevisionToTelevisionDto(t);
            televisionDtos.add(televisionDto);
        }
        return televisionDtos;
    }

    public TelevisionDto getTelevision(Long id) {
        Television t = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television not found"));
        return transferTelevisionToTelevisionDto(t);
    }

    public Long saveTelevision(TelevisionDto televisionDto) {
        Television television = new Television();
        televisionRepository.save(transferTelevisionDtoToTelevision(television, televisionDto));
        return television.getId();
    }

    public void updateTelevision(Long id, TelevisionDto televisionDto) {
        Television t = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television not found"));
        if (t != null) {
            televisionRepository.save(transferTelevisionDtoToTelevision(t, televisionDto));
        }
    }

    public void deleteTelevision(Long id) {
        televisionRepository.deleteById(id);
    }

    private TelevisionDto transferTelevisionToTelevisionDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.id = television.getId();
        televisionDto.brand = television.getBrand();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        televisionDto.availableSize = television.getAvailableSize();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenType = television.getScreenType();
        televisionDto.screenQuality = television.getScreenQuality();
        televisionDto.smartTv = television.getSmartTv();
        televisionDto.wifi = television.getWifi();
        televisionDto.voiceControl = television.getVoiceControl();
        televisionDto.hdr = television.getHdr();
        televisionDto.bluetooth = television.getBluetooth();
        televisionDto.ambiLight = television.getAmbiLight();
        televisionDto.originalStock = television.getOriginalStock();
        televisionDto.sold = television.getSold();
        televisionDto.remoteController = television.getRemoteController();
        televisionDto.ciModule = television.getCiModule();
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();
        for (WallBracket wallBracket : television.getWallBrackets()) {
            wallBracketDtos.add(wallBracketService.getWallBracket(wallBracket.getId()));
        }
        televisionDto.wallBrackets = wallBracketDtos;
        return televisionDto;
    }

    private Television transferTelevisionDtoToTelevision(Television television, TelevisionDto televisionDto) {
        television.setBrand(televisionDto.brand);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        television.setAvailableSize(televisionDto.availableSize);
        television.setRefreshRate(televisionDto.refreshRate);
        television.setScreenType(televisionDto.screenType);
        television.setScreenQuality(televisionDto.screenQuality);
        television.setSmartTv(televisionDto.smartTv);
        television.setWifi(televisionDto.wifi);
        television.setVoiceControl(televisionDto.voiceControl);
        television.setHdr(televisionDto.hdr);
        television.setBluetooth(televisionDto.bluetooth);
        television.setAmbiLight(televisionDto.ambiLight);
        television.setOriginalStock(televisionDto.originalStock);
        television.setSold(televisionDto.sold);
        television.setRemoteController(televisionDto.remoteController);
        television.setCiModule(televisionDto.ciModule);
        return television;
    }
}
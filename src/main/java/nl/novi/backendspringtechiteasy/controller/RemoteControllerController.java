package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.dto.RemoteControllerDto;
import nl.novi.backendspringtechiteasy.service.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remote-controllers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @PostMapping
    public ResponseEntity<Long> createRemoteController(RemoteControllerDto remoteControllerDto) {
        Long newId = remoteControllerService.createRemoteController(remoteControllerDto);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }
}

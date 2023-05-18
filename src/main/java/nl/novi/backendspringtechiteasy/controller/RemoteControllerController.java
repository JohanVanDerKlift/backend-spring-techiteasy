package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.dto.RemoteControllerDto;
import nl.novi.backendspringtechiteasy.service.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remote-controllers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @PostMapping
    public ResponseEntity<Object> createRemoteController(@RequestBody RemoteControllerDto remoteControllerDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            Long newId = remoteControllerService.createRemoteController(remoteControllerDto);
            return new ResponseEntity<>(newId, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<RemoteControllerDto>> getAllRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable Long id) {
        return ResponseEntity.ok(remoteControllerService.getRemoteController(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable Long id, @RequestBody RemoteControllerDto remoteControllerDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            remoteControllerService.updateRemoteController(remoteControllerDto, id);
            return  ResponseEntity.ok("Remote controller was updated");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}

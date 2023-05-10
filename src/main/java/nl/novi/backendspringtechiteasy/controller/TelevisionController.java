package nl.novi.backendspringtechiteasy.controller;

import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import nl.novi.backendspringtechiteasy.model.Television;
import nl.novi.backendspringtechiteasy.repository.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    private final TelevisionRepository repos;

    public TelevisionController(TelevisionRepository repos) {
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<Iterable<Television>> getTelevisions() {
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Television>> getTelevision(@PathVariable Long id) {
        if (id >= 0 && id < repos.count()) {
            return ResponseEntity.ok(repos.findById(id));
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        repos.save(television);
        return ResponseEntity.ok(television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        if (id >= 0 && id < repos.count()) {
            Television t = repos.findById(id).orElseThrow();
            t.setAmbiLight(television.getAmbiLight());
            t.setBluetooth(television.getBluetooth());
            t.setAvailableSize(television.getAvailableSize());
            t.setBrand(television.getBrand());
            t.setHdr(television.getHdr());
            t.setName(television.getName());
            t.setPrice(television.getPrice());
            t.setRefreshRate(television.getRefreshRate());
            t.setOriginalStock(television.getOriginalStock());
            t.setScreenQuality(television.getScreenQuality());
            t.setSold(television.getSold());
            t.setScreenType(television.getScreenType());
            t.setSmartTv(television.getSmartTv());
            t.setVoiceControl(television.getVoiceControl());
            t.setWifi(television.getWifi());
            repos.save(t);
            return ResponseEntity.ok(t);
        } else if (id >= repos.count()) {
            throw new IndexOutOfBoundsException("ID out of bounds");
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        if (id >= 0 && id < repos.count()) {
            repos.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }
}

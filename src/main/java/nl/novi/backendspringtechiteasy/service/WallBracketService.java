package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.WallBracketDto;
import nl.novi.backendspringtechiteasy.exception.RecordNotFoundException;
import nl.novi.backendspringtechiteasy.model.WallBracket;
import nl.novi.backendspringtechiteasy.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepos;

    public WallBracketService(WallBracketRepository wallBracketRepos) {
        this.wallBracketRepos = wallBracketRepos;
    }

    public Long createWallBracket(WallBracketDto wallBracketDto) {
        WallBracket wallBracket = new WallBracket();
        wallBracketRepos.save(transferWallBracketDtoToWallBracket(wallBracketDto, wallBracket));
        return wallBracket.getId();
    }

    public List<WallBracketDto> getAllWallBrackets() {
        Iterable<WallBracket> wallBrackets = wallBracketRepos.findAll();
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();
        for (WallBracket wallBracket : wallBrackets) {
            WallBracketDto wallBracketDto = transferWallBracketToDto(wallBracket);
            wallBracketDtos.add(wallBracketDto);
        }
        return wallBracketDtos;
    }

    public WallBracketDto getWallBracket(Long id) {
        WallBracket wallBracket = wallBracketRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Wall bracket was not found in the database"));
        return transferWallBracketToDto(wallBracket);
    }

    public void updateWallBracket(Long id, WallBracketDto wallBracketDto) {
        WallBracket wallBracket = wallBracketRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Wall bracket was not found in the database"));
        if (wallBracket != null) {
            wallBracketRepos.save(transferWallBracketDtoToWallBracket(wallBracketDto, wallBracket));
        }
    }

    public void deleteWallBracket(Long id) {
        wallBracketRepos.deleteById(id);
    }

    private WallBracketDto transferWallBracketToDto(WallBracket w) {
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.id = w.getId();
        wallBracketDto.name = w.getName();
        wallBracketDto.price = w.getPrice();
        wallBracketDto.size = w.getSize();
        wallBracketDto.adjustable = w.isAdjustable();
        return wallBracketDto;
    }

    private WallBracket transferWallBracketDtoToWallBracket(WallBracketDto w, WallBracket wallBracket) {
        wallBracket.setName(w.name);
        wallBracket.setPrice(w.price);
        wallBracket.setSize(w.size);
        wallBracket.setAdjustable(w.adjustable);
        return wallBracket;
    }
}

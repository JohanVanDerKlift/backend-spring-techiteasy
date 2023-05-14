package nl.novi.backendspringtechiteasy.service;

import nl.novi.backendspringtechiteasy.dto.TelevisionDto;
import nl.novi.backendspringtechiteasy.model.Television;
import nl.novi.backendspringtechiteasy.repository.TelevisionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    private List<TelevisionDto> getTelevisions() {
        Iterable<Television> televisions = repos.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();
        for (Television t : televisions) {
            TelevisionDto televisionDto = new TelevisionDto();
            BeanUtils.copyProperties(televisionDto, t);
            televisionDtos.add(televisionDto);
        }
        return televisionDtos;
    }

    private TelevisionDto getTelevision(Long id) {
        Optional<Television> t = repos.findById(id);
        TelevisionDto televisionDto = new TelevisionDto();
        BeanUtils.copyProperties(t, televisionDto);
        return televisionDto;
    }

    private
}

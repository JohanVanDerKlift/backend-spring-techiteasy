package nl.novi.backendspringtechiteasy.dto;

import nl.novi.backendspringtechiteasy.model.CIModule;
import nl.novi.backendspringtechiteasy.model.RemoteController;
import nl.novi.backendspringtechiteasy.model.Television;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TelevisionDto {

    public Long id;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;
    public RemoteController remoteController;
    public CIModule ciModule;
    public List<WallBracketDto> wallBrackets;

    public static TelevisionDto fromTelevision(Television television) {
        TelevisionDto dto = new TelevisionDto();
        BeanUtils.copyProperties(television, dto);
        return dto;
    }
}

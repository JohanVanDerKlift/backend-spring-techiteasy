package nl.novi.backendspringtechiteasy.dto;

import nl.novi.backendspringtechiteasy.model.Television;
import org.springframework.beans.BeanUtils;

public class TelevisionInputDto {
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

    public Television toTelevision() {
        Television television = new Television();
        BeanUtils.copyProperties(this, television);
        return television;
    }
}

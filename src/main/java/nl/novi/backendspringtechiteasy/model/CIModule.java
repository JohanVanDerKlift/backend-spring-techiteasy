package nl.novi.backendspringtechiteasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CIModule {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Double price;
    @OneToMany(mappedBy = "ciModule")
    @JsonIgnore
    private List<Television> televisions;

    public CIModule() {
    }

    public CIModule(Long id, String name, String type, Double price, List<Television> televisions) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.televisions = televisions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}

package lt.kb.real_estate.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "buildings")
public class Building {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Address address;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private Double size;
    private BigDecimal marketValue;
    @NotNull
    @Enumerated(EnumType.STRING)

    @Column(name = "type", columnDefinition = "ENUM('apartment','house','industrial')", nullable = false)
    private PropertyType propertyType;

    public Building() {
    }

    public Building(Long id, Address address, Owner owner, Double size, BigDecimal marketValue, PropertyType propertyType) {
        this.id = id;
        this.address = address;
        this.owner = owner;
        this.size = size;
        this.marketValue = marketValue;
        this.propertyType = propertyType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", address=" + address +
                ", size=" + size +
                ", marketValue=" + marketValue +
                ", propertyType=" + propertyType +
                '}';
    }

}

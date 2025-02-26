package org.exercise.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be null")
    private String name;

    @NotBlank(message = "Description must not be null")
    private String description;

    @NotBlank(message = "Image must not be null")
    @Lob
    private String image;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price cannot be a negative number")
    private Integer price;

    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers;

    public List<SpecialOffer> getSpecialOffers() {
        return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {

        return this.id + ' ' + this.name + ' ' + this.description + ' ' + this.image + ' ' + this.price;
    }

}

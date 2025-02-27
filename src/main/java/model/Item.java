package model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String category;
    private String location;
    private String image;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private int UserId;
    public Item() {}
    public Item(String name, String description, String category, String location, ItemStatus status, ItemType type, int userId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.status = status;
        this.type = type;
        UserId = userId;
    }

    public Item(String name, String description, String category, String location, String image, ItemStatus status, ItemType type, int userId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.status = status;
        this.type = type;
        UserId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && UserId == item.UserId && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(category, item.category) && Objects.equals(location, item.location) && Objects.equals(image, item.image) && status == item.status && type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, location, image, status, type, UserId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

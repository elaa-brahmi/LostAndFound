package model;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private LocalDate datefound;
    private int UserId;
    public Item() {}
    public Item(Integer id, String name, String description, String category, String location, String image, LocalDate date, ItemStatus itemStatus, Integer userid, ItemType itemType) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.datefound = date;
        this.status = itemStatus;
        UserId = userid;
        type = itemType;

    }

    public Item(String name, String description, String category, String location, String image,LocalDate datefound, ItemType type, int userId) {
        this.name = name;
        this.datefound = datefound;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.type = type;
        UserId = userId;
    }

    public Item(int id, String image, String location, String category, String description, String name, LocalDate datefound, int userId, ItemType type) {
        this.id = id;
        this.image = image;
        this.location = location;
        this.category = category;
        this.description = description;
        this.name = name;
        this.datefound = datefound;
        UserId = userId;
        this.type = type;
    }

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

    public Item(Integer id, String name, String description, String category, String location, String image, LocalDate date, String status, Integer userid, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.image = image;
        this.datefound = date;
        UserId = userid;
        this.type = itemType;

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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", datefound=" + datefound +
                ", UserId=" + UserId +
                '}';
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

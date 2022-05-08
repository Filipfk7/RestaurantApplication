

public class MenuItem {
    private int id;
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;



    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvaiable(Boolean available) {
        isAvailable = available;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public MenuItem(int id,String name, String description, Double price, Boolean isAvailable){
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;

    }
    public MenuItem(String name, String description, Double price, Boolean isAvailable){
        this.description = description;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;

    }
}

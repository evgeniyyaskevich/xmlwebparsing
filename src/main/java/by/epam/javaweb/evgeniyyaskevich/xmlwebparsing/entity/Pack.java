package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public class Pack {
    private PackType packType = PackType.BOX;
    private Integer amount;
    private Double price;

    public PackType getPackType() {
        return packType;
    }

    public void setPackType(PackType packType) {
        this.packType = packType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pack pack = (Pack) o;
        return packType == pack.packType &&
                Objects.equals(amount, pack.amount) &&
                Objects.equals(price, pack.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packType, amount, price);
    }
}

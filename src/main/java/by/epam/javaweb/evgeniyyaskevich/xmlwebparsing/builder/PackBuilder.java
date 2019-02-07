package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Pack;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.PackType;

public class PackBuilder {
    private PackType packType = PackType.BOX;
    private Integer amount;
    private Double price;

    public PackBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public PackBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public PackBuilder setPackType(PackType packType) {
        this.packType = packType;
        return this;
    }

    public Pack build() {
        Pack newPackage = new Pack();
        newPackage.setPackType(this.packType);
        newPackage.setAmount(this.amount);
        newPackage.setPrice(this.price);
        return newPackage;
    }
}

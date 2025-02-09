package net.musiciansstore.storeApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "instruments")
@Data
public class Instrument {
    private ObjectId id;
    private String instrumentName;
    private Double price;
    private Integer stocks;
}

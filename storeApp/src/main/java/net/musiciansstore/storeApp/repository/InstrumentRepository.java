package net.musiciansstore.storeApp.repository;

import net.musiciansstore.storeApp.entity.Instrument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstrumentRepository extends MongoRepository<Instrument, ObjectId> {
}

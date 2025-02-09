package net.musiciansstore.storeApp.service;

import net.musiciansstore.storeApp.entity.Instrument;
import net.musiciansstore.storeApp.repository.InstrumentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InstrumentService {
    @Autowired
    private InstrumentRepository _instrumentRepository;
    public List<Instrument> getAll()
    {
        try {
            return _instrumentRepository.findAll();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ArrayList<Instrument>();
        }
    }
    public boolean saveEntry(Instrument instrument)
    {
        try {
            _instrumentRepository.save(instrument);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void deleteById(ObjectId id)
    {
        try {
            _instrumentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public Optional<Instrument> findById(ObjectId id)
    {
        return _instrumentRepository.findById(id);
    }

}

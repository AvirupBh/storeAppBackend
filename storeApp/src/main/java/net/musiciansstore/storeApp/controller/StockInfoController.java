package net.musiciansstore.storeApp.controller;

import net.musiciansstore.storeApp.entity.Instrument;
import net.musiciansstore.storeApp.service.InstrumentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("stock-info")
public class StockInfoController {
    @Autowired
    private InstrumentService _instrumentService;
    @GetMapping("all")
    public ResponseEntity<List<Instrument>> getAllInstruments()
    {
         List<Instrument> instruments=_instrumentService.getAll();
         if(instruments!=null && !instruments.isEmpty())
         {
             return new ResponseEntity<List<Instrument>>(instruments,HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public boolean insertNewInstrument(@RequestBody Instrument instrument)
    {
        return _instrumentService.saveEntry(instrument);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Instrument> findById(@PathVariable ObjectId id)
    {
        Optional<Instrument> instrument=_instrumentService.findById(id);
        return instrument.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> removeInstrument(@PathVariable ObjectId id)
    {
        try{
            _instrumentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("id/{id}")
    public ResponseEntity<?> updateInstrumentInfo(@PathVariable ObjectId id, @RequestBody Instrument newInstrument)
    {
        Optional<Instrument> old=_instrumentService.findById(id);
        if(old.isPresent())
        {
            Instrument oldInstrument=old.get();
            oldInstrument.setInstrumentName((newInstrument.getInstrumentName()!=null)? newInstrument.getInstrumentName() : oldInstrument.getInstrumentName());
            oldInstrument.setStocks((newInstrument.getStocks()!=null)? newInstrument.getStocks(): oldInstrument.getStocks());
            oldInstrument.setPrice((newInstrument.getPrice()!=null)?newInstrument.getPrice():oldInstrument.getPrice());
            _instrumentService.saveEntry(oldInstrument);
            return new ResponseEntity<>(oldInstrument, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

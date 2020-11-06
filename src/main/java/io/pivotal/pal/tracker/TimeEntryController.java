package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    //@RequestMapping(value="/time-entries", method = RequestMethod.POST, produces ="application/json")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    //@RequestMapping(value="/time-entries/{timeEntryId}", method = RequestMethod.GET, produces ="application/json")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry response = timeEntryRepository.find(timeEntryId);
        if(response!=null){
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/time-entries")
    //@RequestMapping(value="/time-entries", method = RequestMethod.GET, produces ="application/json")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    //@RequestMapping(value="/time-entries/{timeEntryId}", method = RequestMethod.PUT, produces ="application/json")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry response = timeEntryRepository.update(timeEntryId,expected);
        if(response!=null){
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

   @DeleteMapping("/time-entries/{timeEntryId}")
    //@RequestMapping(value="/time-entries/{timeEntryId}", method = RequestMethod.DELETE, produces ="application/json")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

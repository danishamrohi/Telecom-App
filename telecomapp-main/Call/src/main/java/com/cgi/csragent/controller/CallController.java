package com.cgi.csragent.controller;

import com.cgi.csragent.model.Call;
import com.cgi.csragent.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/call")
public class CallController {

    CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> addCall(@RequestBody Call call){
        call.setDateAdded(LocalDate.now());
        System.out.println(call);
        boolean isCreated = callService.addCall(call);
        if (isCreated){
            return new ResponseEntity<>(call, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Call Not Created", HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<?> getAllCalls(){
        List<Call> callList = callService.getAllCalls();
        return new ResponseEntity<>(callList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/all-with-ph/{phoneNumber}")
    public ResponseEntity<?> getCallsByPhone(@PathVariable ("phoneNumber") int phoneNumber){
        var list = callService.getCallsWithCustomerPhoneNumber(phoneNumber);
        if (list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/all-with-date/{date}")
    public ResponseEntity<?> getCallsByDate(@PathVariable ("date") String date){
        //        Date format is yyyy-MM-dd
        List<Call> list = callService.getCallsbyDate(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        if (list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/all-with-source/{callSource}")
    public ResponseEntity<?> getAllCallsByCallSource(@PathVariable ("callSource") String callSource){
        List<Call> list = callService.getCallsbyCallSource(callSource);
        if (list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }
    
    @CrossOrigin
    @GetMapping("/all-with-csrid/{csrId}")
    public ResponseEntity<?> getAllCallsByName(@PathVariable ("csrId") int csrId){
        List<Call> list = callService.getCallsbyCsrId(csrId);
        if (list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{callId}")
    public ResponseEntity<?> deleteCall(@PathVariable ("callId") String callId){
       boolean isDeleted = callService.deleteCall(callId);
       if (isDeleted){
           return new ResponseEntity<>(isDeleted, HttpStatus.OK);
       }
        return new ResponseEntity<>(isDeleted, HttpStatus.NOT_FOUND);
    }



}

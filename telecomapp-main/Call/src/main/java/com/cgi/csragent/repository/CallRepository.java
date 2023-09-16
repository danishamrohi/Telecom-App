package com.cgi.csragent.repository;

import com.cgi.csragent.model.Call;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CallRepository extends MongoRepository<Call, String> {
    List<Call> findCallByCustomerPhoneNumber(int customerPhoneNumber);
    List<Call> findCallByCsrId(int csrId);
    List<Call> findCallByDateAdded(LocalDate date);
    List<Call> findCallByCallSource(String callSource);
}

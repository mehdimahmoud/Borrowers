package com.mmik.challenge.capita.restcontroller;

import com.mmik.challenge.capita.domain.Borrower;
import com.mmik.challenge.capita.domain.enumtype.Checked;
import com.mmik.challenge.capita.repository.BorrowerRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Implementation of Borrowers services API
 * Created by mmik on 29/01/2017.
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    static Logger log = LoggerFactory.getLogger(BorrowerController.class);

    @Autowired
    private BorrowerRepository borrowerRepository;

    @PersistenceContext
    private EntityManager em;

/*    @Operation(summary = "sayHello", description = "Returns a greeting message")
    @RequestMapping(value = "/",method = GET)
    public String sayHello(){
        return "Hello, you are at our Borrowers services API.";
    }*/

    @Operation(summary = "getAllBorrowers", description = "getAllBorrowers")
    @RequestMapping(value = "/", method = GET)
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    @Operation(summary = "getBorrower", description = "Get Borrower")
    @RequestMapping(value = "/{borrowerId}", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public Borrower getBorrower(@PathVariable String borrowerId) {
        Optional<Borrower> oBw = borrowerRepository.findById(Long.valueOf(borrowerId));
        if(oBw.isPresent())
            return oBw.get();
        else {
            throw new IllegalArgumentException("There can't be found the searched borrower, due to borrowerId is null or doesn't exist.");
        }
    }

    @Operation(summary = "searchBorrower", description = "Search Borrower via criteria")
    @RequestMapping(value = "/search", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public Borrower searchBorrower(@RequestParam String fullName) {
        Optional<Borrower> oBw = borrowerRepository.findByFullNameContaining(fullName);
        if(oBw.isPresent())
            return oBw.get();
        else {
            throw new IllegalArgumentException("There can't be found the searched borrower, due to Id is null or doesn't exist.");
        }
    }

    @Operation(summary = "addBorrower", description = "Registers new Borrower")
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Borrower addBorrower(Borrower borrower) {
        Optional<Borrower> oBw = borrowerRepository.save(borrower);
        if(oBw.isPresent()) {
            return oBw.get();
        }else {
            throw new IllegalArgumentException("A problem occurred while saving a new borrower.");
        }
    }

    @Operation(summary = "checkedBorrower", description = "Validates some details of Borrower after a verify")
    @PutMapping(value = "/validate/{borrowerId}")
    @ResponseStatus(HttpStatus.OK)
    public Borrower checkedBorrower(@PathVariable String borrowerId) {
        Borrower bw = getBorrower(borrowerId);
        bw.setChecked(Checked.T);
        Optional<Borrower> oBw = borrowerRepository.save(bw);
        if(oBw.isPresent()) {
            return oBw.get();
        }else {
            throw new IllegalArgumentException("Can't find the borrower ID ["+borrowerId+"] for updating his data.");
        }
    }

    @Operation(summary = "updateBorrower", description = "Updates some details of Borrower")
    @PutMapping(value = "/{borrowerId}")
    public Borrower updateBorrower(@PathVariable String borrowerId, String barCode, String borrowerType) throws IllegalAccessException {
        Borrower bw = getBorrower(borrowerId);
        // Check before if the Borrower details are checked or not:
        if(bw.getChecked()!=Checked.T)
            throw new IllegalAccessException("Can't update before checking some details of the Borrower ID ["+borrowerId+"] ");
        // As details are Checked (="T") then continue updating:
        bw.setBarCode(barCode);
        bw.setType(borrowerType);
        Optional<Borrower> oBw = borrowerRepository.save(bw);
        if(oBw.isPresent()) {
            return oBw.get();
        }else {
            throw new IllegalArgumentException("Can't find the Borrower ID ["+borrowerId+"] for updating his data.");
        }
    }

    @Operation(summary = "deleteBorrower", description = "Deletes a Borrower")
    @DeleteMapping(value = "/{borrowerId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBorrower(@PathVariable String borrowerId) {
        try {
            if(!StringUtils.hasText(borrowerId)) {
                throw new IllegalArgumentException("Borrower can't be deleted, due to ID is null.");
            }

            borrowerRepository.deleteById(Long.valueOf(borrowerId));
            return new StringBuilder("Borrower ID [")
                    .append(borrowerId)
                    .append("]")
                    .append(" deleted successfully.")
                    .toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Borrower can't be deleted, due to Borrower ID ["+borrowerId+"] is malformed : "+e.getMessage());
        }
    }

}

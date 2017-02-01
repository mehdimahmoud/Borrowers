package com.mmik.challenge.capita.restcontroller;

import com.mmik.challenge.capita.domain.Borrower;
import com.mmik.challenge.capita.domain.enumtype.Checked;
import com.mmik.challenge.capita.repository.BorrowerRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@RequestMapping("/borrower")
public class BorrowerController {

    static Logger log = LoggerFactory.getLogger(BorrowerController.class);

    @Autowired
    private BorrowerRepository borrowerRepository;

    @PersistenceContext
    private EntityManager em;

    @ApiOperation(value = "sayHello", nickname = "sayHello")
    @RequestMapping(value = "/",method = GET)
    public String sayHello(){
        return "Hello, you are at our Borrowers services API.";
    }

    @ApiOperation(value = "getAllBorrowers", nickname = "getAllBorrowers")
    @RequestMapping(value = "/all", method = GET)
    public List<Borrower> getAllBorrowers() {
        Stream<Borrower> sBw = borrowerRepository.findAll();
        return sBw.collect(Collectors.toList());
    }

    @ApiOperation(value = "getBorrower", nickname = "Get Borrower")
    @RequestMapping(value = "/{borrowerId}", method = GET)
    public Borrower getBorrower(@PathVariable String borrowerId) {
        Optional<Borrower> oBw = borrowerRepository.findOne(Long.valueOf(borrowerId));
        if(oBw.isPresent())
            return oBw.get();
        else {
            throw new IllegalArgumentException("There can't be found the searched borrower, due to Id is null or doesn't exist.");
        }
    }

    @ApiOperation(value = "searchBorrower", nickname = "Search Borrower via criteria")
    @RequestMapping(value = "/search", method = GET)
    public Borrower searchBorrower(@RequestParam String fullName) {
        Optional<Borrower> oBw = borrowerRepository.findByFullNameContaining(fullName);
        if(oBw.isPresent())
            return oBw.get();
        else {
            throw new IllegalArgumentException("There can't be found the searched borrower, due to Id is null or doesn't exist.");
        }
    }

    @ApiOperation(value = "addBorrower", nickname = "Registers new Borrower")
    @RequestMapping(value = "/register/", method = POST)
    public Borrower addBorrower(Borrower borrower) {
        Optional<Borrower> oBw = borrowerRepository.save(borrower);
        if(oBw.isPresent()) {
            return oBw.get();
        }else {
            throw new IllegalArgumentException("A problem occurred while saving a new borrower.");
        }
    }

    @ApiOperation(value = "checkedBorrower", nickname = "Validates some details of Borrower")
    @RequestMapping(value = "/validate/{borrowerId}", method = PUT)
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

    @ApiOperation(value = "updateBorrower", nickname = "Updates some details of Borrower")
    @RequestMapping(value = "/update/{borrowerId}", method = PUT)
    public Borrower updateBorrower(@PathVariable String borrowerId, String barCode, String borrowerType) throws IllegalAccessException {
        Borrower bw = getBorrower(borrowerId);
        // Check before if the Borrower details are checked or not:
        if(bw.getChecked()!=Checked.T)
            throw new IllegalAccessException("Can't update before checking some details of the Borrower  ["+borrowerId+"] ");
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

    @ApiOperation(value = "deleteBorrower", nickname = "Deletes Borrower")
    @RequestMapping(value = "/delete/{borrowerId}", method = PUT)
    public String deleteBorrower(@PathVariable String borrowerId) {
        try {
            if(StringUtils.isEmpty(borrowerId))
                throw new IllegalArgumentException("Borrower can't be deleted, due to ID is null.");

            borrowerRepository.delete(Long.valueOf(borrowerId));
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

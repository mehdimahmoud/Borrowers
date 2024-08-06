package com.mmik.challenge.capita.repository;

import com.mmik.challenge.capita.domain.Borrower;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by mmik on 29/01/2017.
 */
@Transactional
public interface BorrowerRepository extends Repository<Borrower,Long> {
    Optional<Borrower> findById(Long id);
    Optional<Borrower> findByFullNameContaining(String fullName);
    Optional<Borrower> save(Borrower borrower);
    void deleteById(Long id);

    List<Borrower> findAll();
}

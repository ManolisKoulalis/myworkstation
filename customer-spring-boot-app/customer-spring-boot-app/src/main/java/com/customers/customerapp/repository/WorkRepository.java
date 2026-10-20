
package com.customers.customerapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.customers.customerapp.model.Work;

public interface WorkRepository extends JpaRepository<Work, Integer> {

	
/*
 * Xρησιμοποιουμε  JPQL αντι για SQL
* αυτο το κανουμε καθως ετσι μας δινεται η δυνατοτητα να ορισουμε την ευρεση μας με βαση τα Entities και των fields που εχουν
 * ετσι γλιτωνουμε να κανουμε εξτρα join πινακων χειροκινητα
* αυτο το αναλαμβανει το hibernate το οποιο αντιλαμβανεται την σχεση που υπαρχει μεταξυ customer-work αφου την δηλωσαμε στα entity
* με αποτελεσμα να κανει μονο του τα join και να μας δινει το απο τελεσμα
* Τελος χρησιμοποιουμε query καθως στην αναζητηση που κανουμε το work δεν περιεχει το customerId απο μονο του ετσι το επαναπροσδιοριζουμε 
*/
    @Query("""
        SELECT w
        FROM Customer c
        JOIN c.worklist w
        WHERE c.id = :customerId
        """)
    List<Work> findAllByCustomerId(@Param("customerId") Integer customerId);

    @Query("""
        SELECT w
        FROM Customer c
        JOIN c.worklist w
        WHERE c.id = :customerId AND w.id = :workId
        """)
    Optional<Work> findByIdAndCustomerId(@Param("workId") Integer workId,@Param("customerId") Integer customerId);
}


package com.example.talent4gig;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InputXMLRepository extends JpaRepository<InputXML, Long>{

}
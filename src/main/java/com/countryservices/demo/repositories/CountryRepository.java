package com.countryservices.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.countryservices.demo.beans.Country;
@Component
@Service
public interface CountryRepository extends JpaRepository<Country,Integer> {

}

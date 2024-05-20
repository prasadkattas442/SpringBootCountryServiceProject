package com.countryservices.demo.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.countryservices.demo.beans.Country;
import com.countryservices.demo.controllers.AddResponse;
import com.countryservices.demo.repositories.CountryRepository;

@Component
@Service
public class CountryService {
	
	
	  static HashMap<Integer, Country> countryIdMap;
	  
	  public CountryService()
	  {
		  countryIdMap=new HashMap<Integer, Country>();
	  Country indiaCountry=new Country(1, "India", "Delhi");
	  Country usaCountry=new  Country(2, "USA", "Washington"); 
	  Country ukCountry=new Country(3, "UK", "London");
	  
	  countryIdMap.put(1, indiaCountry); 
	  countryIdMap.put(2, usaCountry);
	  countryIdMap.put(3, ukCountry); 
	  
	  }
	 
	@Autowired
	CountryRepository countryrep;
	
	public List<Country> getAllCountries() 
	{
		//return countryrep.findAll();
		List<Country> countries=new ArrayList<Country>(countryIdMap.values());
		return countries; 
	}
	public Country getCountrybyID(int id) {
		return countryrep.findById(id).get();
		/*Country country=countryIdMap.get(id); return country; */
		
	}
	public Country getCountrybyName(String countryName) {
		/*
		 * Country country=null; for(int i:countryIdMap.keySet()) {
		 * if(countryIdMap.get(i).getCountryName().equals(countryName))
		 * country=countryIdMap.get(i); } return country;
		 */
		List<Country> countries=countryrep.findAll();
		Country country=null;
		for(Country con:countries) 
		{
			if(con.getCountryName().equalsIgnoreCase(countryName))
				country=con;
		}
		return country;
		
			}
	public Country addCountry(Country country) 
	{
		country.setId(getMaxId());
		countryrep.save(country);
		return country;
		/*
		 * country.setId(getMaxId()); countryIdMap.put(country.getId(), country); return
		 * country;
		 */
	}//utility method to get maxID
	public int getMaxId() {
		/*
		 * int max=0; for(int id:countryIdMap.keySet()) if(max<=id) max=id; return
		 * max+1;
		 */
		return countryrep.findAll().size()+1;
	}
	public Country updateCountry(Country country) 
	{
		countryrep.save(country);
		return country;
		/*
		 * if(country.getId()>0) countryIdMap.put(country.getId(), country); return
		 * country;
		 */
	}
	public AddResponse deleteCountry(int id) 
	{
		countryrep.deleteById(id);
		AddResponse res=new AddResponse();
		res.setMsg("Country deleted !");
		res.setId(id);
		return res;
		/*
		 * countryIdMap.remove(id); AddResponse res=new AddResponse();
		 * res.setMsg("Country deleted..."); res.setId(id); return res;
		 */
		
	}
}























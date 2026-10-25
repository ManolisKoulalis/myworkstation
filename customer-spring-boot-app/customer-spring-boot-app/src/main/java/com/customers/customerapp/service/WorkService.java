package com.customers.customerapp.service;



import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.customers.customerapp.dto.AddressDto;
import com.customers.customerapp.dto.WorkRequestDto;
import com.customers.customerapp.dto.WorkResponseDto;
import com.customers.customerapp.model.Address;
import com.customers.customerapp.model.Customer;
import com.customers.customerapp.model.Work;
import com.customers.customerapp.repository.CustomerRepository;
import com.customers.customerapp.repository.WorkRepository;

public class WorkService {

	
	private final WorkRepository workRepository;
	private final CustomerRepository customerRepository;

	public WorkService(WorkRepository workRepository,CustomerRepository customerRepository) {
		this.workRepository = workRepository;
		this.customerRepository = customerRepository;
	}
	
	
	
	@Transactional(readOnly = true)
	public Work findWorkById(Integer workId) {
		
		Work work=workRepository.findById(workId).orElseThrow(() -> new IllegalArgumentException( "Δεν βρέθηκε εργασια με ID: " + workId));
		
		return work;
	}
	
	
	@Transactional(readOnly = true)
	public WorkResponseDto getWorkById(Integer workId, Integer customerId) {
		
		Work work= findWorkByIdAndCustomerId(workId,customerId);
		
		WorkResponseDto workResponseDto=toWorkResponseDto(work);
		
		
		return workResponseDto;
	}
	
	// Διαγραφη του work
	@Transactional
	public void deleteWork(Integer workId, Integer customerId) {
		
		 // Βρίσκουμε την εργασία και ελέγχουμε τη σύνδεσή της
		Work work=findWorkByIdAndCustomerId(workId, customerId);
		
		  // Βρίσκουμε τον πελάτη
		Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
	                new IllegalArgumentException(
	                    "Δεν βρέθηκε πελάτης με ID: " + customerId
	                )
	            );
		
		
		 // Αφαιρούμε την εργασία από τη λίστα του πελάτη
		 // εφοσον εχουμε orphanRemoval = true σημαίνει ότι όταν μια εργασία αφαιρεθεί από τη συλλογή worklist του πελάτη, το Hibernate μπορεί να διαγράψει και την αντίστοιχη Work Entity από τη βάση
	     customer.removeWork(work);
	     // αλλιως θα θελαμε και workRepository.delete(work);
		
	}
	
	// Η λιστα του καθε customer
	 @Transactional(readOnly = true)
	    public List<WorkResponseDto> getWorksByCustomerId(Integer customerId) {

	        List<Work> works = workRepository.findAllByCustomerId(customerId);

	        return works.stream().map(work -> toWorkResponseDto(work)).toList();
	    }
	
	
	
	 public Integer createWork(Integer customerId,WorkRequestDto dto) {

		    // 1. Βρίσκουμε τον πελάτη
		    Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
		                new IllegalArgumentException(
		                    "Δεν βρέθηκε πελάτης με ID: " + customerId
		                )
		            );

		    // 2. Ελέγχουμε τα ποσά
		    if (dto.getPaidCharge().compareTo(dto.getChargeCost()) > 0) {
		        throw new IllegalArgumentException(
		            "Το πληρωμένο ποσό δεν μπορεί να υπερβαίνει τη χρέωση"
		        );
		    }

		    // 3. Δημιουργούμε τη νέα εργασία
		    Work work = new Work();

		    work.setWorkType(dto.getWorkType());
		    work.setConstructionAddress(toAddress(dto.getConstructionAddress()));
		    work.setChargeCost(dto.getChargeCost());
		    work.setPaidCharge(dto.getPaidCharge());
		    work.setMoreInfo(dto.getMoreInfo());

		    // 4. Συνδέουμε την εργασία με τον πελάτη
		    customer.addWork(work);
		    
		    //workRepository.save(work); δεν χρειαζεται καθως στην συσχτιση τους εχουμε cascade.ALL οποτε οταν θα κανουμε save(customer) θα αποθηκευτει και το work
		    
		    // 5. Αποθηκεύουμε μέσω του Customer, βεβαια επειδη ο Customer που ανακτήσαμε είναι ήδη managed μέσα στο transaction, το save(customer) δεν είναι αυστηρά απαραίτητο για να εντοπιστεί η αλλαγή της συλλογής
		    customerRepository.saveAndFlush(customer);

		    // 6. Επιστρέφουμε το ID της νέας εργασίας
		    return work.getId();
		}
	 
	 
	 
	 @Transactional
	 public void updateWork(Integer workId,Integer customerId, WorkRequestDto dto) {

	     // 1. Βρίσκουμε την εργασία του συγκεκριμένου πελάτη
	     Work work = findWorkByIdAndCustomerId( workId, customerId);

	     // 2. Ελέγχουμε τα ποσά
	   
	     /* Τα ελεγχουμε με annotations εν χρειαζονται εδω
	     if (dto.getChargeCost() == null || dto.getPaidCharge() == null) {
	         throw new IllegalArgumentException(
	             "Τα ποσά είναι υποχρεωτικά"
	         );
	     }

	     if (dto.getChargeCost().signum() < 0 || dto.getPaidCharge().signum() < 0) {
	         throw new IllegalArgumentException(
	             "Τα ποσά δεν μπορούν να είναι αρνητικά"
	         );
	     }
	     
	     */
	     if (dto.getPaidCharge().compareTo(dto.getChargeCost()) > 0) {
	         throw new IllegalArgumentException(
	             "Το πληρωμένο ποσό δεν μπορεί να υπερβαίνει τη χρέωση"
	         );
	     }

	     // 3. Ενημερώνουμε την υπάρχουσα Work Entity
	     work.setWorkType(dto.getWorkType());

	     work.setConstructionAddress(toAddress(dto.getConstructionAddress())
	     );

	     work.setChargeCost(dto.getChargeCost());
	     work.setPaidCharge(dto.getPaidCharge());
	     work.setMoreInfo(dto.getMoreInfo());

	     // Δεν χρειάζεται workRepository.save(work)
	     // επειδή η Work Entity είναι managed.
	 }
	 
	 
	 
	 
	 
	 
	 
	 // για να μην επαναλαμβανουμε τον κωδικα στο update και στο getWorkById
	 private Work findWorkByIdAndCustomerId(Integer workId,Integer customerId) {

		    return workRepository.findByIdAndCustomerId(workId, customerId) .orElseThrow(() ->
		                new IllegalArgumentException(
		                    "Δεν βρέθηκε εργασία με ID: " + workId
		                )
		            );
		} 
	 
	 
	// Converters of Entities and Dtos
	
	private WorkResponseDto toWorkResponseDto(Work work) {
		
		 
		WorkResponseDto dto= new WorkResponseDto();
		
		dto.setWorkType(work.getWorkType());
		dto.setChargeCost(work.getChargeCost());
		dto.setMoreInfo(work.getMoreInfo());
		dto.setPaidCharge(work.getPaidCharge());
		dto.setRemainingCharge(work.getRemainingCharge());
		
		AddressDto workAddressdto= new AddressDto();
		workAddressdto=toAddressDto(work.getConstructionAddress());
		
		dto.setConstructionAddress(workAddressdto);
		
		return dto;
	}
	
	
	private AddressDto toAddressDto(Address address) {

	    if (address == null) {
	    	 throw new IllegalArgumentException(
	 	            "Η διεύθυνση είναι υποχρεωτική"
	 	        );
	    }

	    AddressDto dto = new AddressDto();

	    dto.setStreetName(address.getStreetName());
	    dto.setPostcode(address.getPostcode());

	    return dto;
	}
	
	private Address toAddress(AddressDto dto) {

	    if (dto == null) {
	        throw new IllegalArgumentException(
	            "Η διεύθυνση είναι υποχρεωτική"
	        );
	    }

	    Address address = new Address();

	    address.setStreetName(dto.getStreetName());
	    address.setPostcode(dto.getPostcode());

	    return address;
	}
	
	
}

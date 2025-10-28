package com.empapp.service;

import com.empapp.model.Contacts;
import com.empapp.model.Employees;
import com.empapp.dto.ContactsDTO;
import com.empapp.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactsService {

    private final ContactsRepository repository;

    public ContactsDTO toDTO(Contacts c) {
        ContactsDTO dto = new ContactsDTO();
        dto.setContactId(c.getContactId());
        dto.setEmployeeId(c.getEmployee().getEmployeeId());
        dto.setEmployeeName(c.getEmployee().getFirstName() + " " + c.getEmployee().getLastName());
        dto.setAddressLine1(c.getAddressLine1());
        dto.setAddressLine2(c.getAddressLine2());
        dto.setCity(c.getCity());
        dto.setState(c.getState());
        dto.setPostalCode(c.getPostalCode());
        dto.setEmailOfficial(c.getEmailOfficial());
        dto.setEmailPersonal(c.getEmailPersonal());
        dto.setPhoneMobile(c.getPhoneMobile());
        dto.setEmergencyContactName(c.getEmergencyContactName());
        dto.setEmergencyContactPhone(c.getEmergencyContactPhone());
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUpdatedAt(c.getUpdatedAt());
        return dto;
    }

    public Contacts toEntity(ContactsDTO dto, Employees employee) {
        Contacts c = new Contacts();
        c.setContactId(dto.getContactId());
        c.setEmployee(employee);
        c.setAddressLine1(dto.getAddressLine1());
        c.setAddressLine2(dto.getAddressLine2());
        c.setCity(dto.getCity());
        c.setState(dto.getState());
        c.setPostalCode(dto.getPostalCode());
        c.setEmailOfficial(dto.getEmailOfficial());
        c.setEmailPersonal(dto.getEmailPersonal());
        c.setPhoneMobile(dto.getPhoneMobile());
        c.setEmergencyContactName(dto.getEmergencyContactName());
        c.setEmergencyContactPhone(dto.getEmergencyContactPhone());
        return c;
    }

    public ContactsDTO saveContact(ContactsDTO dto, Employees employee) {
        Contacts saved = repository.save(toEntity(dto, employee));
        return toDTO(saved);
    }

    public List<ContactsDTO> getAllContacts() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ContactsDTO getContactById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public List<ContactsDTO> getContactsByEmployee(Employees employee) {
        return repository.findByEmployee(employee).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
	
	public List<ContactsDTO> toDTOList(List<Contacts> contactsList) {
    return contactsList.stream()
            .map(this::toDTO)
            .toList(); 
	}
	
	public List<Contacts> toEntityList(List<ContactsDTO> dtoList) {
    return dtoList.stream()
            .map(dto -> {
                Employees employee = new Employees();
                employee.setEmployeeId(dto.getEmployeeId());
                return toEntity(dto, employee);
            })
            .toList();
	}
}

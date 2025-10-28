package com.empapp.controller;

import com.empapp.dto.ContactsDTO;
import com.empapp.model.Employees;
import com.empapp.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsService contactsService;

    // POST /api/contacts
    @PostMapping
    public ContactsDTO createContact(@RequestBody ContactsDTO dto) {
        Employees employee = new Employees();
        employee.setEmployeeId(dto.getEmployeeId());
        return contactsService.saveContact(dto, employee);
    }

    // GET /api/contacts
    @GetMapping
    public List<ContactsDTO> getAllContacts() {
        return contactsService.getAllContacts();
    }

    // GET /api/contacts/{id}
    @GetMapping("/{id}")
    public ContactsDTO getContactById(@PathVariable Long id) {
        return contactsService.getContactById(id);
    }

    // GET /api/contacts/employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public List<ContactsDTO> getContactsByEmployee(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return contactsService.getContactsByEmployee(employee);
    }

    // DELETE /api/contacts/{id}
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactsService.deleteContact(id);
    }
}

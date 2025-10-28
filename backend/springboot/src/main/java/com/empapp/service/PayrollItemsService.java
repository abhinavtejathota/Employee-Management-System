package com.empapp.service;

import com.empapp.dto.PayrollItemsDTO;
import com.empapp.model.Employees;
import com.empapp.model.Payroll;
import com.empapp.model.PayrollItems;
import com.empapp.repository.EmployeesRepository;
import com.empapp.repository.PayrollItemsRepository;
import com.empapp.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollItemsService {

    private final PayrollItemsRepository repository;
    private final EmployeesRepository employeesRepository;
    private final PayrollRepository payrollRepository;

    // Entity -> DTO
    public PayrollItemsDTO toDTO(PayrollItems item) {
        PayrollItemsDTO dto = new PayrollItemsDTO();
        dto.setItemId(item.getItemId());
        dto.setPayrollId(item.getPayroll() != null ? item.getPayroll().getPayrollId() : null);
        dto.setEmployeeId(item.getEmployee() != null ? item.getEmployee().getEmployeeId() : null);
        dto.setEmployeeName(item.getEmployee() != null ?
                item.getEmployee().getFirstName() + " " + item.getEmployee().getLastName() : null);
        dto.setGrossAmount(item.getGrossAmount());
        dto.setTax(item.getTax());
        dto.setDeductions(item.getDeductions());
        dto.setNetAmount(item.getNetAmount());
        dto.setPaid(item.getPaid());
        dto.setPaymentMethod(item.getPaymentMethod());
        dto.setNotes(item.getNotes());
        dto.setPaidAt(item.getPaidAt());
        return dto;
    }

    // DTO -> Entity
    public PayrollItems toEntity(PayrollItemsDTO dto) {
        PayrollItems item = new PayrollItems();
        item.setItemId(dto.getItemId());
        item.setGrossAmount(dto.getGrossAmount());
        item.setTax(dto.getTax());
        item.setDeductions(dto.getDeductions());
        item.setNetAmount(dto.getNetAmount());
        item.setPaid(dto.getPaid());
        item.setPaymentMethod(dto.getPaymentMethod());
        item.setNotes(dto.getNotes());
        item.setPaidAt(dto.getPaidAt());

        if (dto.getEmployeeId() != null) {
            Employees emp = employeesRepository.findById(dto.getEmployeeId()).orElse(null);
            item.setEmployee(emp);
        }

        if (dto.getPayrollId() != null) {
            Payroll payroll = payrollRepository.findById(dto.getPayrollId()).orElse(null);
            item.setPayroll(payroll);
        }

        return item;
    }

    public List<PayrollItemsDTO> toDTOList(List<PayrollItems> items) {
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // CRUD
    public PayrollItemsDTO createPayrollItem(PayrollItemsDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public List<PayrollItemsDTO> getAllPayrollItems() {
        return toDTOList(repository.findAll());
    }

    public PayrollItemsDTO getPayrollItemById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public List<PayrollItemsDTO> getItemsByPayroll(Payroll payroll) {
        return toDTOList(repository.findByPayroll(payroll));
    }

    public List<PayrollItemsDTO> getItemsByEmployee(Employees employee) {
        return toDTOList(repository.findByEmployee(employee));
    }

    public List<PayrollItemsDTO> getUnpaidItemsByPayroll(Payroll payroll) {
        return toDTOList(repository.findByPayrollAndPaidFalse(payroll));
    }

    public PayrollItemsDTO markAsPaid(Long itemId) {
        return repository.findById(itemId).map(item -> {
            item.setPaid(true);
            item.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));
            return toDTO(repository.save(item));
        }).orElse(null);
    }

    public void deletePayrollItem(Long id) {
        repository.deleteById(id);
    }
}

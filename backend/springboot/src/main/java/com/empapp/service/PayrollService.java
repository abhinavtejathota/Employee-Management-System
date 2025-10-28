package com.empapp.service;

import com.empapp.dto.PayrollDTO;
import com.empapp.dto.PayrollItemsDTO;
import com.empapp.model.Payroll;
import com.empapp.model.PayrollItems;
import com.empapp.model.Users;
import com.empapp.repository.EmployeesRepository;
import com.empapp.repository.PayrollRepository;
import com.empapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final UsersRepository usersRepository;
    private final EmployeesRepository employeesRepository;
    private final PayrollItemsService payrollItemsService;

    // -------------------- Conversion --------------------

    public PayrollDTO toDTO(Payroll payroll) {
        PayrollDTO dto = new PayrollDTO();
        dto.setPayrollId(payroll.getPayrollId());
        dto.setPeriodStart(payroll.getPeriodStart());
        dto.setPeriodEnd(payroll.getPeriodEnd());
        dto.setStatus(payroll.getStatus());
        dto.setCreatedAt(payroll.getCreatedAt());
        dto.setCreatedByUsername(payroll.getCreatedBy() != null ? payroll.getCreatedBy().getUsername() : null);

        if (payroll.getPayrollItems() != null) {
            List<PayrollItemsDTO> payrollItemsDTO = payroll.getPayrollItems().stream()
                    .map(payrollItemsService::toDTO)
                    .collect(Collectors.toList());

            dto.setPayrollItems(payrollItemsDTO);

            // Calculate total amount
            dto.setTotalAmount(
                    payrollItemsDTO.stream()
                            .mapToDouble(item -> item.getNetAmount().doubleValue())
                            .sum()
            );
        } else {
            dto.setTotalAmount(0.0);
        }

        return dto;
    }

    public Payroll toEntity(PayrollDTO dto) {
        Payroll payroll = new Payroll();
        payroll.setPayrollId(dto.getPayrollId());
        payroll.setPeriodStart(dto.getPeriodStart());
        payroll.setPeriodEnd(dto.getPeriodEnd());
        payroll.setStatus(dto.getStatus());

        if (dto.getCreatedByUsername() != null) {
            Users user = usersRepository.findAll().stream()
                    .filter(u -> u.getUsername().equals(dto.getCreatedByUsername()))
                    .findFirst()
                    .orElse(null);
            payroll.setCreatedBy(user);
        }

        if (dto.getPayrollItems() != null) {
            List<PayrollItems> items = dto.getPayrollItems().stream()
                    .map(payrollItemsService::toEntity)
                    .peek(item -> item.setPayroll(payroll)) // maintain bidirectional link
                    .collect(Collectors.toList());
            payroll.setPayrollItems(items);
        }

        return payroll;
    }

    // -------------------- CRUD Operations --------------------

    @Transactional
    public PayrollDTO createPayroll(PayrollDTO dto) {
        Payroll saved = payrollRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PayrollDTO getPayrollById(Long id) {
        return payrollRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public List<PayrollDTO> getPayrollsByUser(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            log.warn("No user found for ID: {}", userId);
            return List.of();
        }
        return payrollRepository.findByCreatedBy(user).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PayrollDTO> getPayrollsByStatus(Payroll.PayrollStatus status) {
        return payrollRepository.findByStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PayrollDTO updateStatus(Long payrollId, Payroll.PayrollStatus status) {
        return payrollRepository.findById(payrollId)
                .map(payroll -> {
                    payroll.setStatus(status);
                    return toDTO(payrollRepository.save(payroll));
                })
                .orElse(null);
    }

    public void deletePayroll(Long payrollId) {
        payrollRepository.deleteById(payrollId);
    }
}

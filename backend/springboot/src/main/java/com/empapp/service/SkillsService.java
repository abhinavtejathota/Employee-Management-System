package com.empapp.service;

import com.empapp.dto.SkillsDTO;
import com.empapp.model.Skills;
import com.empapp.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;

    // Create or Update skill
    public SkillsDTO createSkill(SkillsDTO dto) {
        Skills saved = skillsRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public List<SkillsDTO> getAllSkills() {
        return skillsRepository.findAll().stream().map(this::toDTO).toList();
    }

    public SkillsDTO getSkillById(Long id) {
        return skillsRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public SkillsDTO getSkillByName(String skillName) {
        return skillsRepository.findBySkillName(skillName).map(this::toDTO).orElse(null);
    }

    public void deleteSkill(Long id) {
        skillsRepository.deleteById(id);
    }

    // ---------------- Conversion ----------------
    public SkillsDTO toDTO(Skills skill) {
        if (skill == null) return null;
        SkillsDTO dto = new SkillsDTO();
        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());
        return dto;
    }

    public Skills toEntity(SkillsDTO dto) {
        Skills skill = new Skills();
        skill.setSkillId(dto.getSkillId());
        skill.setSkillName(dto.getSkillName());
        return skill;
    }
}

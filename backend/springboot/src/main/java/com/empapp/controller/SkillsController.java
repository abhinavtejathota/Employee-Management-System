package com.empapp.controller;

import com.empapp.dto.SkillsDTO;
import com.empapp.service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @PostMapping
    public SkillsDTO createSkill(@RequestBody SkillsDTO dto) {
        return skillsService.createSkill(dto);
    }

    @GetMapping
    public List<SkillsDTO> getAllSkills() {
        return skillsService.getAllSkills();
    }

    @GetMapping("/{id}")
    public SkillsDTO getSkillById(@PathVariable Long id) {
        return skillsService.getSkillById(id);
    }

    @GetMapping("/skname/{skillName}")
    public SkillsDTO getSkillByName(@PathVariable String skillName) {
        return skillsService.getSkillByName(skillName);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillsService.deleteSkill(id);
    }
}

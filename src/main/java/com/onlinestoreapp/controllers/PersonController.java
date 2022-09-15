package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.dtos.person.PersonDto;
import com.onlinestoreapp.services.PersonService;
import com.onlinestoreapp.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class PersonController {

    private final PersonService personService;
    private final RolesService rolesService;

    @Autowired
    public PersonController(PersonService personService, RolesService rolesService) {
        this.personService = personService;
        this.rolesService = rolesService;
    }

    @GetMapping("/user-edit/{id}")
    public String getUserEditPage(@PathVariable("id") Integer personId, Model model) {
        PersonDto personDto = personService.getPersonDtoById(personId);

        model.addAttribute("personDTO", personDto);
        model.addAttribute("roles", rolesService.findAll());
        return "/admin-control/user-control/user-edit";
    }

    @PatchMapping("/{id}")
    public String proceedUserEdit(@ModelAttribute("personDTO") PersonDto personDto,
                                  @PathVariable("id") Integer id) {
        personDto.setId(id);
        personService.editPerson(personDto);

        return "redirect:/admin/admin-panel";
    }

    @GetMapping("/{id}")
    public String proceedUserDelete(@PathVariable("id") Integer id) {
        personService.deletePerson(id);

        return "redirect:/admin/admin-panel";
    }
}

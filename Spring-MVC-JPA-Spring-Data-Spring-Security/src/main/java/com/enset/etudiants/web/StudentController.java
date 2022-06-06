package com.enset.etudiants.web;

import com.enset.etudiants.entities.Genre;
import com.enset.etudiants.entities.Student;
import com.enset.etudiants.repositories.StudentRepository;
import com.enset.etudiants.services.IStudentServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@AllArgsConstructor
public class StudentController {
    private IStudentServices studentServices;
    private StudentRepository studentRepository;


    @GetMapping(path = "/")
    public String home(){
        return "home";
    }

    @GetMapping(path = "/index")
    public String students(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "6") int size,
                           @RequestParam(defaultValue = "") String keyword){
        Page<Student> listStudents = studentServices.findStudentBykeyword(keyword,PageRequest.of(page,size));

        int[] pagesList = new int[listStudents.getTotalPages()];
        model.addAttribute("keyword",keyword);
        model.addAttribute("studentList",listStudents.getContent());
        model.addAttribute("pages",pagesList);
        model.addAttribute("lastPage",pagesList.length-1);
        model.addAttribute("currentPage",page);
        return "students";
    }


    @GetMapping(path = "/addStudent")
    public String addStudent(Model model){
        model.addAttribute("newStudent",new Student());
        Map<Genre, Object> sexes = new HashMap<Genre, Object>();
        sexes.put(Genre.MASCULIN, 1);
        sexes.put(Genre.FEMININ, 0);
        model.addAttribute("sexes", sexes);
        return "new_student";
    }


    @PostMapping(path = "save")
    public String saveStudnet(Model model , Student student){
        studentServices.saveStudent(student);
        return "redirect:/index";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id){
        studentServices.deleteStudent(id);
        return "redirect:/index";
    }

    @GetMapping(path = "/edit/{id}")
    public String editStudent(Model model ,@PathVariable(value = "id") Long id){
        model.addAttribute("att_editStudent",studentServices.findStudent(id));
        Map<Genre, Object> sexes = new HashMap<Genre, Object>();
        sexes.put(Genre.MASCULIN, 1);
        sexes.put(Genre.FEMININ, 0);
        model.addAttribute("sexes", sexes);
        return "updateStudent";
    }

/*    @GetMapping(path = "/findStudent")
    public String findStudent(Model model ,@RequestParam(defaultValue = "") String keyword){
        Page<Student> patientPages = studentRepository.findByNomContaining(keyword, PageRequest.of(1,10));
        model.addAttribute("listPatients",patientPages.getContent());
        model.addAttribute("pages",new int[patientPages.getTotalPages()]);
        model.addAttribute("keyword",keyword);
        return "find_student";
    }*/

}

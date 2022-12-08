package com.nest.studentappnew_backend.controller;

import com.nest.studentappnew_backend.dao.StudentDao;
import com.nest.studentappnew_backend.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentDao dao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public Map<String, String> AddStudent(@RequestBody Students s) {
        System.out.println(s.getName().toString());
        System.out.println(s.getAdmno().toString());
        System.out.println(s.getRollno());
        System.out.println(s.getCollege().toString());
        dao.save(s);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;


    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search",consumes ="application/json",produces = "application/json")
    public List<Students> SearchStudents(@RequestBody  Students s) {
        String admno = s.getAdmno().toString();
        System.out.println(admno);
        return (List<Students>) dao.SearchStudents(s.getAdmno());
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/delete",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> DeleteStudent(@RequestBody Students s) {
        String id=String.valueOf(s.getId());
        System.out.println(id);
        dao.DeleteStudent(s.getId());
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;

    }
    @CrossOrigin(origins = "*")
    @GetMapping("/viewall")
    public List<Students> ViewStudents() {
        return (List<Students>) dao.findAll();
    }
}

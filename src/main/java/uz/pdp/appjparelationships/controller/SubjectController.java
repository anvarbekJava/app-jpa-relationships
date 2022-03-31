package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.main.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //CREATE
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";
        subjectRepository.save(subject);
        return "Subject added";
    }

    //READ
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @DeleteMapping(value = "/deleteSubject/{id}")
        public String deleteSubject(@PathVariable Integer id) {
        try {
            subjectRepository.deleteById(id);
            return "Delete subject";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }
   @PutMapping(value = "/edetSubject/{id}")
           public String edetSubject(@PathVariable Integer id, @RequestBody Subject subject){
            Optional<Subject> optionalSubject = subjectRepository.findById(id);
            if (optionalSubject.isPresent()){
                Subject subjectOne = optionalSubject.get();
                subjectOne.setName(subject.getName());
                return "Edet subject";
            }
            return "Subject not found";
        }


}

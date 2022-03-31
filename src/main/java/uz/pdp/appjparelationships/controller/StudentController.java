package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.main.repository.AddressRepository;
import uz.pdp.appjparelationships.main.repository.GroupRepository;
import uz.pdp.appjparelationships.main.repository.StudentRepository;
import uz.pdp.appjparelationships.main.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;
    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }
    //3. FACULTY DEKANAT
    @GetMapping(value = "/forFaculty/{facultyId}")
    public Page<Student> getStudentForFaculty(@PathVariable Integer facultyId, @RequestParam int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Student> studentForFaculty = studentRepository.findAllByGroup_FacultyId(facultyId, pageRequest);
        return studentForFaculty;
    }

    //4. GROUP OWNER
    @GetMapping(value = "/forGroup/{groupId}")
    public  Page<Student> getStudentGroup(@PathVariable Integer groupId, @RequestParam int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Student> studentForGroup = studentRepository.findAllByGroupId(groupId, pageRequest);
        return studentForGroup;
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Integer id){
        try {
            studentRepository.deleteById(id);
            return "Delete student";
        }catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PostMapping(value = "/addStudent")
    public String addStudent(@RequestBody StudentDto studentDto){
        boolean exists = studentRepository.existsByFirstNameAndLastNameAndGroupId(studentDto.getFirstName(), studentDto.getLastName(), studentDto.getGroupId());
        if (exists) {
            Student student = new Student();
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
            if (!optionalGroup.isPresent())
                return "Not found group";
            Group group = optionalGroup.get();
            student.setGroup(group);
            Address address = new Address();
            address.setDistrict(studentDto.getDistrict());
            address.setStreet(studentDto.getStreet());
            address.setCity(studentDto.getCity());

            Address saveAddress = addressRepository.save(address);
            student.setAddress(saveAddress);
            List<Subject> subjectList = new ArrayList<>();
            for (Integer subject : studentDto.getSubjects()) {
                Optional<Subject> subjectOptional = subjectRepository.findById(subject.intValue());
                if (!subjectOptional.isPresent())
                    return "Subject not found";
                Subject subjectOne = subjectOptional.get();
                subjectList.add(subjectOne);
            }
            student.setSubjects(subjectList);

            return "Save Student";
        }
         return "Student est in baza";
    }

    @PutMapping(value = "/edetStudent/{id}")
    public String edetStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
            if (!optionalGroup.isPresent())
                return "Not found group";
            Group group = optionalGroup.get();
            student.setGroup(group);
            Optional<Address> optionalAddress = addressRepository.findById(studentDto.getGroupId());
            Address address = optionalAddress.get();
            address.setDistrict(studentDto.getDistrict());
            address.setStreet(studentDto.getStreet());
            address.setCity(studentDto.getCity());

            Address saveAddress = addressRepository.save(address);
            student.setAddress(saveAddress);
            List<Subject> subjectList = new ArrayList<>();
            for (Integer subject : studentDto.getSubjects()) {
                Optional<Subject> subjectOptional = subjectRepository.findById(subject.intValue());
                if (!subjectOptional.isPresent())
                    return "Subject not found";
                Subject subjectOne = subjectOptional.get();
                subjectList.add(subjectOne);
            }
            student.setSubjects(subjectList);

            return "Edet Student";
        }
        return "Student not found";

    }

}

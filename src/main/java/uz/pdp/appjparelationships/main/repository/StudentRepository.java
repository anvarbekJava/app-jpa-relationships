package uz.pdp.appjparelationships.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationships.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByFirstNameAndLastNameAndGroupId(String firstName, String lastName, Integer group_id);
    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);
    Page<Student> findAllByGroup_FacultyId(Integer group_faculty_id, Pageable pageable);
    Page<Student> findAllByGroupId(Integer group_id, Pageable pageable);

}

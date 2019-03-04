package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.CoopPositionDto;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.Status;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CoopPostionController {
    @Autowired
    StudentService studentService;

    @Autowired
    CoopPositionService coopPositionService;
    /**
     * Create a new coop position in the database.
     * @param   startDate   The start date of the coop.
     * @param   endDate     The end date of the coop.
     * @param   description Textual description of the coop position
     * @param   term        As string representing the term when the coop is happening.
     * @param   studentId   The id of the student participating in this coop
     * @return  A CoopPositionDto representing the newly added coop
     */
    @PostMapping(value = {"/createCoop", "/createCoop/"})
    public CoopPositionDto createCoopPostion(@RequestParam(name = "startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
                                             @RequestParam(name = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "location") String location, @RequestParam(name = "term") String term,
                                             @RequestParam(name = "studentId") int studentId) throws IllegalArgumentException {
        Student student = studentService.getStudentById(studentId);
        CoopPosition coopPostion = coopPositionService.createCoopPosition(startDate, endDate, description, location, term, student);
        studentService.offerCoopPostionToStudent(student.getStudentID(),coopPostion.getCoopId());
        return DtoConverters.convertToDto(coopPostion);
    }

    /**
     * Get all coop positions in the system.
     * @return a list of CoopPositionDto representing all coop positions in the system.
     */
    @GetMapping(value = {"/coops", "/coops/"})
    public List<CoopPositionDto> getAllCoop() {
        List<CoopPositionDto> coopDtos = new ArrayList<>();
        for (CoopPosition cp : coopPositionService.getAllCoopPositions()) {
            coopDtos.add(DtoConverters.convertToDto(cp));
        }
        return coopDtos;
    }

    /**
     * Set the status of a coop position
     * @param cpId The Id of the coop for which the status is being set.
     * @param status The status to be used.
     * @return A CoopPositionDto representing the modified coop, null if a coop with such Id cannot be found.
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/setCoopStatus", "/setCoopStatus/"})
    public CoopPositionDto adjudicateCoop(@RequestParam(name = "coopId") int cpId, @RequestParam(name="status")Status status)
            throws IllegalArgumentException {
        CoopPosition cp = coopPositionService.getById(cpId);
        if(cp==null)
            return null;
        else cp.setStatus(status);
        return DtoConverters.convertToDto(cp);
    }

}
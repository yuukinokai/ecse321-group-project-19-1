package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dto.CoopPositionDto;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.StudentService;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;
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

	@Autowired
	UserEntityService userEntityService;

	/**
	 * Create a new coop position in the database.
	 *
	 * @param startDate   The start date of the coop.
	 * @param endDate     The end date of the coop.
	 * @param description Textual description of the coop position
	 * @param term        As string representing the term when the coop is
	 *                    happening.
	 * @param studentId   The id of the student participating in this coop
	 * @return A CoopPositionDto representing the newly added coop
	 */
	@PostMapping(value = { "/createCoop", "/createCoop/" })
	public CoopPositionDto createCoopPostion(
			@RequestParam(name = "startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
			@RequestParam(name = "endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
			@RequestParam(name = "description") String description, @RequestParam(name = "location") String location,
			@RequestParam(name = "term") String term, @RequestParam(name = "studentId") int studentId)
			throws IllegalArgumentException {
		Student student = studentService.getStudentById(studentId);
		if(student == null) {
			throw new IllegalArgumentException("No such student");
		}
		try {
			CoopPosition coopPostion = coopPositionService.createCoopPosition(startDate, endDate, description, location,
					term, student);
			studentService.offerCoopPostionToStudent(student.getStudentID(), coopPostion.getCoopId());
			return DtoConverters.convertToDto(coopPostion);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Please enter valid information");
		}
	}

	/**
	 * Set the status of a coop position
	 *
	 * @param cpId   The Id of the coop for which the status is being set.
	 * @param status The status to be used.
	 * @return A CoopPositionDto representing the modified coop, null if a coop with
	 *         such Id cannot be found.
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/setCoopStatus", "/setCoopStatus/" })
	public CoopPositionDto adjudicateCoop(@RequestParam(name = "coopId") int cpId,
			@RequestParam(name = "status") Status status, @RequestParam(name = "programManagerEmail") String pmEmail,
			@RequestParam(name = "programManagerPassword") String pmPassword) throws IllegalArgumentException {
		UserEntity ui = userEntityService.getUserEntityByEmail(pmEmail);
		if(ui == null) {
			throw new IllegalArgumentException("No such user");
		}
		if (ui == null || !(ui instanceof ProgramManager) || !Utilities.hash(pmPassword).equals(ui.getPasswordHash()))
			throw new IllegalArgumentException("Access Error");
		try {
			CoopPosition cp = coopPositionService.getById(cpId);
			return DtoConverters.convertToDto(coopPositionService.setCoopPostionStatus(cp, status));
		}catch(Exception e) {
			throw new IllegalArgumentException("Could not find coop");
		}
	}

	/**
	 * delete a coop position
	 * 
	 * @param cpId coop position id
	 * @return true = success
	 */
	@PostMapping(value = { "/deleteCoopPosition", "/deleteCoopPosition/" })
	public boolean deleteDocument(@RequestParam(name = "coopId") int cpId) {
		coopPositionService.deleteCoopPosition(cpId);
		return true;
	}
	
	/**
	 * Get all coop positions in the system for a specified student.
	 *
	 * @return a list of CoopPositionDto representing all coop positions in the
	 *         system belonging to a specified student.
	 */
	@GetMapping(value = { "/coopsByStudent", "/coopsByStudent/" })
	public List<CoopPositionDto> getCoopsByStudent(@RequestParam(value="studentId")int studentId) {
		Student s = studentService.getStudentById(studentId);
		List<CoopPositionDto> coopDtos = new ArrayList<>();
		for (CoopPosition cp : s.getCoopPosition()) {
			coopDtos.add(DtoConverters.convertToDto(cp));
		}
		return coopDtos;
	}
	
	/**
	 * Get all active coop positions in the system.
	 *
	 * @return a list of CoopPositionDto representing all coop positions in the
	 *         system.
	 */
	@GetMapping(value = { "/allCurrentCoops", "/allCurrentCoops/" })
	public List<CoopPositionDto> getAllCurrentCoops() {
		List<CoopPositionDto> coopDtos = new ArrayList<>();
		for(Student s: studentService.getAllStudents()) {
			for(CoopPosition cp: s.getCoopPosition()) {
				if(cp.getEndDate().after(new Date())){
					coopDtos.add(DtoConverters.convertToDto(cp));
				}
			}
		}
		return coopDtos;
	}
	
	/**
	 * Get a coop positions by id in the system.
	 *
	 * @return a CoopPositionDto in the
	 *         system.
	 */
	@GetMapping(value = { "/Coops/{CoopId}", "/Coops/{CoopId}/" })
	public CoopPositionDto getCoopById(@PathVariable(value="CoopId")Integer id) {
		try{
			CoopPositionDto coopDto = DtoConverters.convertToDto(coopPositionService.getById(id));	
			return coopDto;
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Could not find coop");
		}		
	}
	
	/**
	 * Get all coop positions by term in the system.
	 *
	 * @return a list of CoopPositionDto representing all coop positions in the
	 *         system.
	 */
	@GetMapping(value = { "/allCoops/{term}", "/allCoops/{term}/" })
	public List<CoopPositionDto> getAllTermCoops(@PathVariable(value="term")String term) {
		try{
			List<CoopPositionDto> coopDtos = new ArrayList<>();
			for (CoopPosition cp : coopPositionService.getCoopPositionsByTerm(term)) {
				coopDtos.add(DtoConverters.convertToDto(cp));
			}
			return coopDtos;
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Could not find coop");
		}		
	}
	
	/**
	 * Get all coop positions in the system.
	 *
	 * @return a list of CoopPositionDto representing all coop positions in the
	 *         system.
	 */
	@GetMapping(value = { "/allCoops", "/allCoops/" })
	public List<CoopPositionDto> getAllCoops() {
		List<CoopPositionDto> coopDtos = new ArrayList<>();
		for (CoopPosition cp : coopPositionService.getAllCoopPositions()) {
			coopDtos.add(DtoConverters.convertToDto(cp));
		}
		return coopDtos;
	}

}

package org.openmrs.module.hr.db;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.openmrs.module.hr.HrAssignment;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrCompetency;
import org.openmrs.module.hr.HrEducation;
import org.openmrs.module.hr.HrEvaluation;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.HrJobTitle;
import org.openmrs.module.hr.HrLeave;
import org.openmrs.module.hr.HrPost;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffAttribute;
import org.openmrs.module.hr.HrStaffAttributeType;
import org.openmrs.module.hr.HrStaffCert;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;

public interface HRDAO {
	public void saveAssignment(HrAssignment assignment);
	
	public void deleteAssignment(HrAssignment assignment);
	
	public List<HrAssignment> getAssignmentsForPostHistory(HrPostHistory postHistory);
	
	public List<HrAssignment> findAssignmentByExample(HrAssignment assignment);
	
	public HrAssignment getAssignmentById( int id);
	
	public void deleteJobTitle(HrJobTitle jobTitle);
	
	public void saveCertificate(HrCertificate certificate);
	
	public void deleteCertificate(HrCertificate certificate);
	
	public List<HrCertificate> getAllCertificates();
	
	public List<HrCertificate> findCertificatesByExample(HrCertificate certificate);
	
	public HrCertificate getCertificateById( int id);
	
	public void saveCompetency(HrCompetency competency);
	
	public void deleteCompetency(HrCompetency competency) ;
	
	public List<HrCompetency> getAllCompetencies();
	
	public List<HrCompetency> findCompetencyByExample(HrCompetency competency);
	
	public HrCompetency getCompetencyById( int id);
	
	public void saveEducation(HrEducation education);
	
	public void deleteEducation(HrEducation education);
	
	public List<HrEducation> getEducationForStaff(HrStaff staff);
	
	public List<HrEducation> findEducationByExample(HrEducation education);
	
	public HrEducation getEducationById( int id);
	
	public void saveEvaluation(HrEvaluation evaluation);
	
	public void deleteEvaluation(HrEvaluation evaluation);
	
	public List<HrEvaluation> getEvaluationsForStaffAndCompetency(HrStaff staff,HrCompetency competency);
	
	public List<HrEvaluation> findEvaluationByExample(HrEvaluation evaluation);
	
	public HrEvaluation getEvaluationById( int id);
	
	public void saveJobTitle(HrJobTitle jobTitle);
	
	public List<HrJobTitle> getAllJobTitles();
	
	public List<HrJobTitle> findJobTitleByExample(HrJobTitle jobTitle);
	
	public HrJobTitle getJobTitleById( int id);
	
	public void saveLeave(HrLeave leave);
	
	public void deleteLeave(HrLeave leave);
	
	public List<HrLeave> findLeaveByExample(HrLeave leave);
	
	public HrLeave getLeaveById( int id) ;
	
	public void savePostHistory(HrPostHistory postHistory);
	
	public void deletePostHistory(HrPostHistory postHistory);
	
	public List<HrPostHistory> findPostHistoryByExample(HrPostHistory postHistory);
	
	public HrPostHistory getPostHistoryById( int id);
	
	public void savePost(HrPost post);
	
	public void deletePost(HrPost post);
	
	public List<HrPost> findPostByExample(HrPost post);
	
	public List<HrPost> getAllPosts(boolean includeAllPosts,boolean includeAllLocations);
	
	public HrPost getPostById(int id);
	
	public void saveStaff(HrStaff staff);
	
	public void deleteStaff(HrStaff staff);
	
	public List<HrStaff> findStaffByExample(HrStaff staff);
	
	public HrStaff getStaffById( int id);
	
	public void saveStaffAttribute(HrStaffAttribute staffAttribute);
	
	public void deleteStaffAttribute(HrStaffAttribute staffAttribute);
	
	public List<HrStaffAttribute> findStaffAttributeByExample(HrStaffAttribute staffAttribute);
	
	public HrStaffAttribute getStaffAtrributeById( int id);
	
	public void saveStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public void deleteStaffAttributeType(HrStaffAttributeType staffAttributeType);
	
	public List<HrStaffAttributeType> findStaffAttributeTypeByExample(HrStaffAttributeType staffAttributeType);
	
	public HrStaffAttributeType getStaffAttributeTypeById( int id);
	
	public List<HrStaffAttributeType> getAllStaffAttributeTypes();
	
	public void saveStaffCert(HrStaffCert staffCert);
	
	public void deleteStaffCert(HrStaffCert staffCert);
	
	public List<HrStaffCert> findStaffCertByExample(HrStaffCert staffCert);
	
	public HrStaffCert getStaffCertById( int id);
	
	public void saveStaffNote(HrStaffNote staffNote);
	
	public void deleteStaffNote(HrStaffNote staffNote);
	
	public List<HrStaffNote> findStaffNoteByExample(HrStaffNote staffCert);
	
	public List<HrStaffNote> getAllStaffNotes();
	
	public HrStaffNote getStaffNoteById(int id);
	
	public void saveTraining(HrTraining training);
	
	public void deleteTraining(HrTraining training);
	
	public List<HrTraining> findTrainingByExample(HrTraining training);
	
	public HrTraining getTrainingById( int id);
	
	public void saveTrainingClass(HrTrainingClass trainingClass);
	
	public void deleteTrainingClass(HrTrainingClass trainingClass);
	
	public List<HrTrainingClass> findTrainingClassByExample(HrTrainingClass trainingClass) ;
	
	public HrTrainingClass getTrainingClassById( int id);
	
	public void saveTrainPerson(HrTrainPerson trainPerson);
	
	public void deleteTrainPerson(HrTrainPerson trainPerson);
	
	public List<HrTrainPerson> findTrainPersonByExample(HrTrainPerson trainPerson);
	
	public HrTrainPerson getTrainPersonById( int id);
	
	public List<HrIscoCodes> getAllIscoCodes();
	
	public HrIscoCodes getIscoCodeById( String id) ;
	
	public List<HrStaff> getAllStaff(boolean inlcudeAllStaff,boolean includeAllLocations);
	
	public String getMostRecentIncumbentForPostbyId(int id);
	
	public Map<String,Object> getCurrentJobLocationForStaff(int id);
	
	public List<HrPostHistory> getPostHistoriesForStaff(HrStaff staff); 
	
	public HrPostHistory getCurrentPostForStaff(int staffId);
	
	public List<HrPost> getOpenPostByJobTitle(Integer locationId);
	
	public List<HrPost> getPostsByJobTitle(Integer locationId);
	
	public HrStaffAttributeType getStaffAttributeTypeByName(String name) ;
}

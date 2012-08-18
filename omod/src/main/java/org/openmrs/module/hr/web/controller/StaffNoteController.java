package org.openmrs.module.hr.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffNote;
import org.openmrs.module.hr.api.HRNoteService;
import org.openmrs.module.hr.api.propertyEditor.HrStaffNoteEditor;
import org.openmrs.module.hr.api.validator.HRStaffNoteValidator;
import org.openmrs.web.WebConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("staff")
public class StaffNoteController {

    private static final String SUCCESS_FORM_VIEW = "/module/hr/manager/staffNote";
    private static final String SUCCESS_LIST_VIEW = "/module/hr/manager/staffNotes";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
        binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
        binder.registerCustomEditor(HrStaffNote.class, new HrStaffNoteEditor());
    }


    @RequestMapping(value="module/hr/manager/staffNote.form")
    @ModelAttribute("staffNote")
    public HrStaffNote showForm(@RequestParam(value="noteType",required=true)String noteType,@RequestParam(value="noteId",required=false) Integer noteId,@RequestParam(value="parent",required=false)Integer parent, ModelMap model){
        HrStaffNote hrStaffNote;
        HRNoteService hrNoteService = Context.getService(HRNoteService.class);
        if(noteId != null){
            hrStaffNote = hrNoteService.getStaffNoteById(noteId);
            model.addAttribute("childrenNotes", hrStaffNote.getHrStaffNotes());
        }
        else{
            hrStaffNote = new HrStaffNote();
            hrStaffNote.setNoteType(noteType);
            if(parent != null)
                hrStaffNote.setParent(hrNoteService.getStaffNoteById(parent));
            model.addAttribute("childrenNotes",new ArrayList<HrStaffNote>(0));
        }
        return hrStaffNote;
    }

    @RequestMapping(value ="module/hr/manager/staffNote.form", method = RequestMethod.POST)
    public ModelAndView createOrUpdateStaffNote(HttpServletRequest request,@ModelAttribute("staffNote")  HrStaffNote hrStaffNote, BindingResult errors,@ModelAttribute("staff") HrStaff staff){
        HRNoteService hrNoteService = Context.getService(HRNoteService.class);
        ModelAndView modelAndView;


        if(request.getParameter("action").equalsIgnoreCase(Context.getMessageSourceService().getMessage("hr.delete."+hrStaffNote.getNoteType()+".note")))
            deleteStaffNote(request, hrNoteService, hrStaffNote);
        else{
            new HRStaffNoteValidator().validate(hrStaffNote, errors);
            if(errors.hasErrors())
                return new ModelAndView(SUCCESS_FORM_VIEW).addObject("staffNote",hrStaffNote);

            hrStaffNote.setHrStaff(staff);
            hrNoteService.saveNote(hrStaffNote);
            request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, hrStaffNote.getNoteType()+" saved Successfully");
        }

        if(hrStaffNote.getParent() == null){
            modelAndView = new ModelAndView(SUCCESS_LIST_VIEW);
            modelAndView.addObject("staffNotes",hrNoteService.getHeadNotesForStaff(staff, hrStaffNote.getNoteType()));
            modelAndView.addObject("noteType",request.getParameter("noteType"));
        }
        else{
            modelAndView = new ModelAndView(SUCCESS_FORM_VIEW);
            modelAndView.addObject("childrenNotes", hrNoteService.getStaffNoteById(hrStaffNote.getParent().getNoteId()).getHrStaffNotes());
            modelAndView.addObject("staffNote",hrNoteService.getStaffNoteById(hrStaffNote.getParent().getNoteId()));
        }
        return modelAndView;
    }

    private void deleteStaffNote(HttpServletRequest request, HRNoteService hrNoteService, HrStaffNote hrStaffNote) {
        hrNoteService.deleteStaffNote(hrNoteService.getStaffNoteById(hrStaffNote.getNoteId()));
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Note deleted Successfully");

    }

    @RequestMapping(value = "module/hr/manager/staffNotes.list")
    public String showList(HttpServletRequest request,ModelMap model ,@ModelAttribute("staff") HrStaff staff){
        HRNoteService hrNoteService = Context.getService(HRNoteService.class);
        List<HrStaffNote> headNotesForStaff = hrNoteService.getHeadNotesForStaff(staff, request.getParameter("noteType"));
        System.out.println(headNotesForStaff);
        model.addAttribute("staffNotes", headNotesForStaff);
        model.addAttribute("noteType",request.getParameter("noteType"));
        return SUCCESS_LIST_VIEW;
    }

}

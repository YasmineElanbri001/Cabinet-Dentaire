package ma.emsi.dentaire.services;

import ma.emsi.dentaire.entities.Consultation;
import ma.emsi.dentaire.entities.Dentiste;
import ma.emsi.dentaire.repository.ConsultationRepo;
import ma.emsi.dentaire.repository.DentisteRepo;
import ma.emsi.dentaire.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    private ConsultationRepo consultationRepo;

    public ConsultationService(ConsultationRepo consultationRepo){
        this.consultationRepo = consultationRepo;
    }

    public void ajouterConsultation(Consultation consultation){
        consultationRepo.save(consultation);
    }

    public void supprimerConsultation(Long id){
        consultationRepo.deleteById(id);

    }

    public Consultation trouverConsultation(Long id){
        return  consultationRepo.findById(id).orElse(null);
    }

    public List<Consultation> listConsultations(){
        return consultationRepo.findAll();
    }
}

package tn.esprit.cloudnine.Services;

import tn.esprit.cloudnine.Entities.Reclamation;

import java.util.List;

public interface Ireclamation {


        List<Reclamation> getAllReclamations();

        Reclamation getReclamationById(Long id);

        Reclamation createReclamation(Reclamation reclamation);

        Reclamation updateReclamation(Reclamation reclamation);

        void deleteReclamation(Long id);
    }



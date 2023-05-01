package tn.esprit.cloudnine.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.cloudnine.Entities.Reclamation;
import tn.esprit.cloudnine.Repositories.ReclamationRepository;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class IreclamationImp implements Ireclamation {




        private ReclamationRepository reclamationRepository;

        @Override
        public List<Reclamation> getAllReclamations() {
            return reclamationRepository.findAll();
        }

        @Override
        public Reclamation getReclamationById(Long id) {
            return reclamationRepository.findById(id).orElse(null);
        }

        @Override
        public Reclamation createReclamation(Reclamation reclamation) {
            return reclamationRepository.save(reclamation);
        }

        @Override
        public Reclamation updateReclamation(Reclamation reclamation) {
            return reclamationRepository.save(reclamation);
        }

        @Override
        public void deleteReclamation(Long id) {
            reclamationRepository.deleteById(id);
        }
    }



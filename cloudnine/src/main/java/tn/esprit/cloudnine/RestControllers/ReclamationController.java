package tn.esprit.cloudnine.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cloudnine.Entities.Reclamation;
import tn.esprit.cloudnine.Services.EmailService;
import tn.esprit.cloudnine.Services.Ireclamation;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/reclamations")
public class ReclamationController {


    private Ireclamation reclamationService;
    private EmailService emailService;

    @GetMapping("/getAllReclamations")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/getreclam/{id}")
    public Reclamation getReclamationById(@PathVariable Long id) {
        return reclamationService.getReclamationById(id);
    }

    @PostMapping("/ajouterreclamation")
    public Reclamation saveReclamation(@RequestBody Reclamation reclamation) {

        emailService.sendSimpleMessage("yassine.khanfir@esprit.tn","Réclamation","Bonjour\n Votre reclamation sera traiter dans le delais");
        return reclamationService.createReclamation(reclamation);
    }

    @PutMapping("/modifreclamation")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.updateReclamation(reclamation);
    }

    @DeleteMapping("deletereclam/{id}")
    public void deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
    }
}


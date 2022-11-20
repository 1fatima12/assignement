package ma.octo.assignement.web;

import ma.octo.assignement.domain.AuditTransfer;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.transfertService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController(value = "/transfers")
class TransferController {

    public static final int MONTANT_MAXIMAL = 10000;

    Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private CompteRepository rep1;
    @Autowired
    private TransferRepository re2;
    @Autowired
    private transfertService serviceTransfer;
   

    private final UtilisateurRepository re3;

    @Autowired
    TransferController(UtilisateurRepository re3) {
        this.re3 = re3;
    }

    @GetMapping("/listDesTransferts")
    List<Transfer> loadAll() {
        LOGGER.info("Lister des utilisateurs");
        List<Transfer> all = re2.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
           return CollectionUtils.isEmpty(all) ? null : all;
        }
    }

    @GetMapping("/listOfAccounts")
    List<Compte> loadAllCompte() {
        List<Compte> all = rep1.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("/lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> all = re3.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @PostMapping("/executerTransfers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransferDto transferDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
       serviceTransfer.createTransfer(transferDto);
    }
}

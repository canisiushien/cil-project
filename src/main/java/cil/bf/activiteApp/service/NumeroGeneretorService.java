package cil.bf.activiteApp.service;


import cil.bf.activiteApp.domain.NumeroGeneretor;
import cil.bf.activiteApp.domain.Reunion;
import cil.bf.activiteApp.repository.NumeroGeneretorRepository;
import cil.bf.activiteApp.repository.ReunionRepository;
import cil.bf.activiteApp.service.constant.ConstantList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class NumeroGeneretorService {
    private final NumeroGeneretorRepository numeroGeneretorRepository;
    @Autowired
    private ReunionRepository reunionRepository;

    public NumeroGeneretorService(NumeroGeneretorRepository numeroGeneretorRepository) {
        this.numeroGeneretorRepository = numeroGeneretorRepository;


    }



    public Integer getNextNumber(Integer codeTypeReunion) {

       Optional<NumeroGeneretor> da=  numeroGeneretorRepository
                .findByTypeNumeroAndAnnee(codeTypeReunion, LocalDate.now().getYear());


       if(!da.isPresent())
       {
           NumeroGeneretor numeroGeneretors =new NumeroGeneretor();
           numeroGeneretors.setNumero(1);
           numeroGeneretors.setTypeNumero(codeTypeReunion);
           numeroGeneretors.setAnnee(LocalDate.now().getYear());


         numeroGeneretorRepository.save(numeroGeneretors);
          //System.out.println("numeroReunion2a"+numeroGeneretors);
           return  numeroGeneretors.getNumero();
       }
       else
       {
           NumeroGeneretor  numeroGeneretors = da.get();
           numeroGeneretors.setNumero(numeroGeneretors.getNumero()+1);
           numeroGeneretorRepository.save(numeroGeneretors);
           //System.out.println("getNumero"+numeroGeneretors.getNumero());
           return  numeroGeneretors.getNumero();
       }



    }


    @Scheduled(cron = "0 0 0 1 1 ?")
    public void initializeNumber() {


        NumeroGeneretor reunionRCAB = new NumeroGeneretor(0, ConstantList.TypeNumero.RCAB, LocalDate.now().getYear());
        NumeroGeneretor reunionRDIR = new NumeroGeneretor(0, ConstantList.TypeNumero.RDIR, LocalDate.now().getYear());
        NumeroGeneretor reunionRCODIR = new NumeroGeneretor(0, ConstantList.TypeNumero.RCODIR, LocalDate.now().getYear());
        NumeroGeneretor reunionRCF = new NumeroGeneretor(0, ConstantList.TypeNumero.RCF, LocalDate.now().getYear());
        NumeroGeneretor reunionAG = new NumeroGeneretor(0, ConstantList.TypeNumero.AG, LocalDate.now().getYear());
        NumeroGeneretor reunionSO = new NumeroGeneretor(0, ConstantList.TypeNumero.SO, LocalDate.now().getYear());
        NumeroGeneretor reunionSE = new NumeroGeneretor(0, ConstantList.TypeNumero.SE, LocalDate.now().getYear());

        List<NumeroGeneretor> numeroGeneretors = Arrays.asList(reunionRCAB, reunionRDIR,reunionRCODIR,reunionRCF,reunionAG,reunionSO,reunionSE);
        numeroGeneretorRepository.saveAll(numeroGeneretors);
    }
}

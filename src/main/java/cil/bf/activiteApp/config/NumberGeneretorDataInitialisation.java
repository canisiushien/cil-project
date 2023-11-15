package cil.bf.activiteApp.config;
import cil.bf.activiteApp.domain.NumeroGeneretor;
import cil.bf.activiteApp.repository.NumeroGeneretorRepository;
import cil.bf.activiteApp.service.constant.ConstantList;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

//import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
public class NumberGeneretorDataInitialisation {
    private static Logger log = LoggerFactory.getLogger(NumberGeneretorDataInitialisation.class);
    private final NumeroGeneretorRepository numeroGeneretorRepository;

    public NumberGeneretorDataInitialisation(NumeroGeneretorRepository numeroGeneretorRepository) {
        this.numeroGeneretorRepository = numeroGeneretorRepository;
    }

    @PostConstruct
    public void  init() {
        log.debug("number data initialization ...");
        if (numeroGeneretorRepository.count() == 0) {
            NumeroGeneretor reunionRCAB = new NumeroGeneretor(0, ConstantList.TypeNumero.RCAB, LocalDate.now().getYear());
            NumeroGeneretor reunionRDIR = new NumeroGeneretor(0, ConstantList.TypeNumero.RDIR, LocalDate.now().getYear());
            NumeroGeneretor reunionRCODIR = new NumeroGeneretor(0, ConstantList.TypeNumero.RCODIR, LocalDate.now().getYear());
            NumeroGeneretor reunionRCF = new NumeroGeneretor(0, ConstantList.TypeNumero.RCF, LocalDate.now().getYear());
            NumeroGeneretor reunionAG = new NumeroGeneretor(0, ConstantList.TypeNumero.AG, LocalDate.now().getYear());
            NumeroGeneretor reunionSO = new NumeroGeneretor(0, ConstantList.TypeNumero.SO, LocalDate.now().getYear());
            NumeroGeneretor reunionSE = new NumeroGeneretor(0, ConstantList.TypeNumero.SE, LocalDate.now().getYear());

            List<NumeroGeneretor> numeroGeneretors = Arrays.asList(reunionRCAB, reunionRDIR,reunionRCODIR,reunionRCF,reunionAG,reunionSO,reunionSE);
            numeroGeneretorRepository.saveAll(numeroGeneretors);
            //System.out.println(numeroGeneretors);
        }
    }
}

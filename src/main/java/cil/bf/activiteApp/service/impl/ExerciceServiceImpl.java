package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Exercice;
import cil.bf.activiteApp.repository.ExerciceRepository;
import cil.bf.activiteApp.service.ExerciceService;
import cil.bf.activiteApp.service.dto.ExerciceDTO;
import cil.bf.activiteApp.service.mapper.ExerciceMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Slf4j
@Service
public class ExerciceServiceImpl implements ExerciceService {

    private final ExerciceMapper exerciceMapper;
    private final ExerciceRepository exerciceRepository;

    public ExerciceServiceImpl(ExerciceMapper exerciceMapper, ExerciceRepository exerciceRepository) {

        this.exerciceMapper = exerciceMapper;
        this.exerciceRepository = exerciceRepository;
    }

    @Override
    public ExerciceDTO create(ExerciceDTO exerciceDTO) {
        log.info("Creation d'un exercice : {}", exerciceDTO);
        Exercice exercice = exerciceMapper.toEntity(exerciceDTO);

//        if (null != exercice.getId()) {
//            throw new CreateNewElementException();
//        }
        return exerciceMapper.toDto(exerciceRepository.save(exercice));
    }

    @Override
    public ExerciceDTO update(ExerciceDTO exerciceDTO) {
        log.info("Updtae d'un exercice : {}", exerciceDTO);
        Exercice exercice = exerciceMapper.toEntity(exerciceDTO);

//        if (null == exercice.getId()) {
//            throw new UpdateElementException();
//        }
        return exerciceMapper.toDto(exerciceRepository.save(exercice));
    }

    @Override
    public Optional<ExerciceDTO> getById(Long id) {
        log.info("Consultation d'un exercice : {}", id);
        if (exerciceRepository.findById(id).isPresent()) {
            return Optional.ofNullable(exerciceMapper.toDto(exerciceRepository.findById(id).get()));
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public Page<ExerciceDTO> findAll(Pageable pageable) {
        log.info("Pages des exercices");
        return exerciceRepository.findAll(pageable).map(exerciceMapper::toDto);
    }

    @Override
    public List<ExerciceDTO> findAll() {
        log.info("Liste des exercices");
        return exerciceRepository.findAll().stream().map(exerciceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un exercice : {}", id);
        exerciceRepository.deleteById(id);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Direction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface DirectionRepository extends JpaRepository<Direction, Long> {

    Optional<Direction> findBySigle(String sigle);
}

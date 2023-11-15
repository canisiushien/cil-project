package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Privilege;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query("SELECT p FROM Privilege p")
    Stream<Privilege> streamAll();
}

package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Profil;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

    Optional<Profil> findByCode(String code);

    Optional<Profil> findByLibelle(String libelle);

    @EntityGraph(attributePaths = "privileges")
    Optional<Profil> findOneWithPrivilegesByCodeIgnoreCase(String code);

    @Modifying
    @Query(value = "DELETE FROM PROFIL_PRIVILEGE WHERE PROFIL_ID = :id", nativeQuery = true)
    int deleteAssociatePrivilege(@Param("id") Long id);
}

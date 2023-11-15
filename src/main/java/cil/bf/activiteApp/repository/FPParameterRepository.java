package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.FPParameter;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface FPParameterRepository extends JpaRepository<FPParameter, Integer> {

    Optional<FPParameter> findOneByCode(String s);

    Optional<FPParameter> findOndByKeyAndParent(String s, String parent);

    Collection<FPParameter> findAllByParent(String parent);

}

package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.octo.assignement.domain.MoneyDeposit;

@Repository
public interface MoneyDepositRepository extends JpaRepository<MoneyDeposit, Long>{

}

package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.domain.AuditDeposit;

public interface AuditDepositRepository extends JpaRepository<AuditDeposit, Long>{

}

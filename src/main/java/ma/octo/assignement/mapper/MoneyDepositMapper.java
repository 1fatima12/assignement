package ma.octo.assignement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface MoneyDepositMapper {
	@Mapping(source="id",target = "idDto")
	MoneyDepositDto toMoneyDepositDto(MoneyDeposit moneydeposit);
	List<MoneyDepositDto> toMoneyDepositDtos(List<MoneyDeposit> moneydeposits);
	@Mapping(source="idDto",target = "id")
	MoneyDeposit toMoneyDeposit(MoneyDepositDto moneydepositdto);


}

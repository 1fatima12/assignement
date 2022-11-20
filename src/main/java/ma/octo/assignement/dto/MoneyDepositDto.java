package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//utiliser la bibliothéque Lombok

@NoArgsConstructor
@AllArgsConstructor

public class MoneyDepositDto {
	 
	  public Long getIdDto() {
		return idDto;
	}
	public void setIdDto(Long idDto) {
		this.idDto = idDto;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public Date getDateExecution() {
		return dateExecution;
	}
	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}
	public String getNom_prenom_emetteur() {
		return nom_prenom_emetteur;
	}
	public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
		this.nom_prenom_emetteur = nom_prenom_emetteur;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public String getMotifDeposit() {
		return motifDeposit;
	}
	public void setMotifDeposit(String motifDeposit) {
		this.motifDeposit = motifDeposit;
	}
	private Long idDto;
	  private BigDecimal montant;
	  private Date dateExecution;
	  private String nom_prenom_emetteur;
	  private String rib;
	  private String motifDeposit;

}

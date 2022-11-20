package ma.octo.assignement.domain.util;

public enum EventType {

  TRANSFER("transfer"),
  DEPOSIT("Deposit d'argent");

  private String type;

  EventType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

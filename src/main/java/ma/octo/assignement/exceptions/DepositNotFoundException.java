package ma.octo.assignement.exceptions;

public class DepositNotFoundException extends Exception{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DepositNotFoundException() {
		
	}
     public DepositNotFoundException(String message) {
         super(message);		
	}
}

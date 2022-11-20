package ma.octo.assignement.exceptions;

public class MontantNonAutorise extends Exception{
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public MontantNonAutorise() {
        	
        }
        public MontantNonAutorise(String message) {
        	super(message);
        }
}

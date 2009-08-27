/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package system.searchAutomata;


/**
 * The instance variable status indicates the search status at the end of the process.
 * The possible values it may have are:
 * fail - the dialog was unsuccessful. This is the default value.
 * success - at least one taxon reached the goal.
 * cancel - the user canceled the dialog process.
 * error - a processing error occurred.
 * @author Armando
 *
 */
public enum SearchStatus {
	/**
	 * Constantes de la enumeraci&oacute;n
	 */
	FAIL("Fail"),
	SUCCESS("Success"),
	CANCEL("Cancel"),
	ERROR("Error"),
	IDXNOTFOUND("IdxNotFound");
	
	/**
	 * Variable para manejar el valor de la constante
	 */
	private final String status;
	
	/**
	 * Contructor de la enumeraci&oacute;n
	 * @param status
	 */
	SearchStatus(String status) {
		this.status = status;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public String getStatus() {
		return status;
	}	
}

/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package system;

import java.util.List;

/**
 * The SystemParametersManager keeps track of the SukiaSystem's global parameters.
 * @author Armando
 *
 */
public class SystemParametersManager {
	String path; 						// Contains the path (absolute or relative) of the parameters directory.
	String parametersDirectory; 		// Specifies the directory where the default parameter files are located.
	List<String> taxonomicLevelNames; 	// Collection that holds the taxonomic level names valid for the application.
									  	//The ordering of these names matters!
	
	/**
	 * @see "Método taxonomicLevelNamesFileName del protocolo de clase class default en SUKIA SmallTalk"
	 * @return
	 */
	public static String taxonomicLevelNamesFileName() {
		return "taxonLevels.txt";
	}


	/**
	 * @see "Método path del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @see "Método path: del protocolo adding en SUKIA SmallTalk"
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @see "Método parametersDirectory del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getParametersDirectory() {
		return parametersDirectory;
	}

	/**
	 * @see "Método parametersDirectory: del protocolo adding en SUKIA SmallTalk"
	 * @param parametersDirectory
	 */
	public void setParametersDirectory(String parametersDirectory) {
		this.parametersDirectory = parametersDirectory;
	}

	/**
	 * @see "Método taxonomicLevelNames del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<String> getTaxonomicLevelNames() {
		return taxonomicLevelNames;
	}

	/**
	 * Método de instancia agregado 
	 * @param taxonomicLevelNames
	 */
	public void setTaxonomicLevelNames(List<String> taxonomicLevelNames) {
		this.taxonomicLevelNames = taxonomicLevelNames;
	}
	
	/**
	 * @see "Método loadTaxonomicLevelNames del protocolo adding en SUKIA SmallTalk"
	 */
	// Pendiente de traducir (ojo)
	public void loadTaxonomicLevelNames() {
	
	}
}

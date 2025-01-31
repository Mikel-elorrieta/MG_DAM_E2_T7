package modelo;

public class Ikastetxe implements java.io.Serializable {

	/**
	
	 * 
	
	 */

	private static final long serialVersionUID = 5324437671510611081L;

	private int CCEN; // Centro

	private String NOM; // Nombre

	private String NOME; // Nombre extendido

	private String DGENRC; // Descripción General RC

	private String DGENRE; // Descripción General RE

	private String GENR; // Código GENR

	private int MUNI; // Municipio

	private String DMUNIC; // Descripción Municipio

	private String DMUNIE; // Descripción Municipio extendida

	private String DTERRC; // Territorio RC

	private String DTERRE; // Territorio RE

	private String DEPE; // Departamento

	private String DTITUC; // Título de educación

	private String DTITUE; // Título de educación extendido

	private String DOMI; // Dirección

	private int CPOS; // Código postal

	private long TEL1; // Teléfono

	private String TFAX; // Fax

	private String EMAIL; // Correo electrónico

	private String PAGINA; // Página web

	private String COOR_X; // Coordenada X

	private String COOR_Y; // Coordenada Y

	private double LATITUD; // Latitud

	private double LONGITUD; // Longitud

	// Getters y Setters

	public int getCCEN() {

		return CCEN;

	}

	public void setCCEN(int CCEN) {

		this.CCEN = CCEN;

	}

	public String getNOM() {

		return NOM;

	}

	public void setNOM(String NOM) {

		this.NOM = NOM;

	}

	public String getNOME() {

		return NOME;

	}

	public void setNOME(String NOME) {

		this.NOME = NOME;

	}

	public String getDGENRC() {

		return DGENRC;

	}

	public void setDGENRC(String DGENRC) {

		this.DGENRC = DGENRC;

	}

	public String getDGENRE() {

		return DGENRE;

	}

	public void setDGENRE(String DGENRE) {

		this.DGENRE = DGENRE;

	}

	public String getGENR() {

		return GENR;

	}

	public void setGENR(String GENR) {

		this.GENR = GENR;

	}

	public int getMUNI() {

		return MUNI;

	}

	public void setMUNI(int MUNI) {

		this.MUNI = MUNI;

	}

	public String getDMUNIC() {

		return DMUNIC;

	}

	public void setDMUNIC(String DMUNIC) {

		this.DMUNIC = DMUNIC;

	}

	public String getDMUNIE() {

		return DMUNIE;

	}

	public void setDMUNIE(String DMUNIE) {

		this.DMUNIE = DMUNIE;

	}

	public String getDTERRC() {

		return DTERRC;

	}

	public void setDTERRC(String DTERRC) {

		this.DTERRC = DTERRC;

	}

	public String getDTERRE() {

		return DTERRE;

	}

	public void setDTERRE(String DTERRE) {

		this.DTERRE = DTERRE;

	}

	public String getDEPE() {

		return DEPE;

	}

	public void setDEPE(String DEPE) {

		this.DEPE = DEPE;

	}

	public String getDTITUC() {

		return DTITUC;

	}

	public void setDTITUC(String DTITUC) {

		this.DTITUC = DTITUC;

	}

	public String getDTITUE() {

		return DTITUE;

	}

	public void setDTITUE(String DTITUE) {

		this.DTITUE = DTITUE;

	}

	public String getDOMI() {

		return DOMI;

	}

	public void setDOMI(String DOMI) {

		this.DOMI = DOMI;

	}

	public int getCPOS() {

		return CPOS;

	}

	public void setCPOS(int CPOS) {

		this.CPOS = CPOS;

	}

	public long getTEL1() {

		return TEL1;

	}

	public void setTEL1(long TEL1) {

		this.TEL1 = TEL1;

	}

	public String getTFAX() {

		return TFAX;

	}

	public void setTFAX(String TFAX) {

		this.TFAX = TFAX;

	}

	public String getEMAIL() {

		return EMAIL;

	}

	public void setEMAIL(String EMAIL) {

		this.EMAIL = EMAIL;

	}

	public String getPAGINA() {

		return PAGINA;

	}

	public void setPAGINA(String PAGINA) {

		this.PAGINA = PAGINA;

	}

	public String getCOOR_X() {

		return COOR_X;

	}

	public void setCOOR_X(String COOR_X) {

		this.COOR_X = COOR_X;

	}

	public String getCOOR_Y() {

		return COOR_Y;

	}

	public void setCOOR_Y(String COOR_Y) {

		this.COOR_Y = COOR_Y;

	}

	public double getLATITUD() {

		return LATITUD;

	}

	public void setLATITUD(double LATITUD) {

		this.LATITUD = LATITUD;

	}

	public double getLONGITUD() {

		return LONGITUD;

	}

	public void setLONGITUD(double LONGITUD) {

		this.LONGITUD = LONGITUD;

	}

	@Override
	public String toString() {
		return "Ikastetxe [CCEN=" + CCEN + ", NOM=" + NOM + ", NOME=" + NOME + ", DGENRC=" + DGENRC + ", DGENRE="
				+ DGENRE + ", GENR=" + GENR + ", MUNI=" + MUNI + ", DMUNIC=" + DMUNIC + ", DMUNIE=" + DMUNIE
				+ ", DTERRC=" + DTERRC + ", DTERRE=" + DTERRE + ", DEPE=" + DEPE + ", DTITUC=" + DTITUC + ", DTITUE="
				+ DTITUE + ", DOMI=" + DOMI + ", CPOS=" + CPOS + ", TEL1=" + TEL1 + ", TFAX=" + TFAX + ", EMAIL="
				+ EMAIL + ", PAGINA=" + PAGINA + ", COOR_X=" + COOR_X + ", COOR_Y=" + COOR_Y + ", LATITUD=" + LATITUD
				+ ", LONGITUD=" + LONGITUD + "]";
	}
	
	

}
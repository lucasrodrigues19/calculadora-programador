package conversor_numerico.modelo;

public enum OperacoesConversores {

	BINARIO(1), DECIMAL(2), HEXA_DECIMAL(3);

	private int code;

	private OperacoesConversores(int code) {
		this.code = code;
	}
	
	public static OperacoesConversores valueOf(int value) {
		for(OperacoesConversores ope: OperacoesConversores.values()) {
			if(value == ope.getCode())
				return ope;
		}
		return null;
	}
	

	public int getCode() {
		return code;
	}

}

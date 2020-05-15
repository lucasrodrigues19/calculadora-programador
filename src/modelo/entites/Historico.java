package modelo.entites;

public class Historico {

	private Integer 		hisid;
	private String 			hisdado;
	private Integer 		hisusuid;
	private Usuario 		hisusuario;
	
	
	public Historico(Integer hisid, String hisdado, Usuario hisusuario) {
		this.hisid = hisid;
		this.hisdado = hisdado;
		this.hisusuid = hisusuario.getUsuid();
		this.hisusuario = hisusuario;
	}

	public Historico() {
		
	}

	public Integer getHisid() {
		return hisid;
	}

	public void setHisid(Integer hisid) {
		this.hisid = hisid;
	}

	public String getHisdado() {
		return hisdado;
	}

	public void setHisdado(String hisdado) {
		this.hisdado = hisdado;
	}

	public Integer getHisusuid() {
		return hisusuid;
	}

	public Usuario getHisusuario() {
		return hisusuario;
	}

	public void setHisusuario(Usuario hisusuario) {
		this.hisusuario = hisusuario;
		this.hisusuid = hisusuario.getUsuid();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hisid == null) ? 0 : hisid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		if (hisid == null) {
			if (other.hisid != null)
				return false;
		} else if (!hisid.equals(other.hisid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Historico [hisid=" + hisid + ", hisdado=" + hisdado + ", hisusuid=" + hisusuid + "]";
	}
	
}

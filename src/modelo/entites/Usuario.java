package modelo.entites;

import java.util.List;

public class Usuario {

	private Integer 			usuid;
	private String 				usunome;
	private String 				usuemail;
	private String 				usutelefone;
	private List<Historico> 	usuhistorico;
	private List<Logs> usulogs;

	public Usuario(Integer usuid, String usunome, String usuemail, String usutelefone, List<Historico> usuhistorico,
			List<Logs> usulogs) {
		this.usuid = usuid;
		this.usunome = usunome;
		this.usuemail = usuemail;
		this.usutelefone = usutelefone;
		this.usuhistorico = usuhistorico;
		this.usulogs = usulogs;
	}

	public Usuario(Integer usuid, String usunome, String usuemail, String usutelefone, List<Historico> usuhistorico) {
		this.usuid = usuid;
		this.usunome = usunome;
		this.usuemail = usuemail;
		this.usutelefone = usutelefone;
		this.usuhistorico = usuhistorico;
	}

	public Usuario(Integer usuid, String usunome, String usuemail, List<Logs> usulogs, String usutelefone) {
		this.usuid = usuid;
		this.usunome = usunome;
		this.usuemail = usuemail;
		this.usutelefone = usutelefone;
		this.usulogs = usulogs;
	}

	public Usuario() {
	}

	public Integer getUsuid() {
		return usuid;
	}

	public void setUsuid(Integer usuid) {
		this.usuid = usuid;
	}

	public String getUsunome() {
		return usunome;
	}

	public void setUsunome(String usunome) {
		this.usunome = usunome;
	}

	public String getUsuemail() {
		return usuemail;
	}

	public void setUsuemail(String usuemail) {
		this.usuemail = usuemail;
	}

	public String getUsutelefone() {
		return usutelefone;
	}

	public void setUsutelefone(String usutelefone) {
		this.usutelefone = usutelefone;
	}

	public List<Historico> getUsuhistorico() {
		return usuhistorico;
	}

	public void setUsuhistorico(List<Historico> usuhistorico) {
		this.usuhistorico = usuhistorico;
	}

	public List<Logs> getUsulogs() {
		return usulogs;
	}

	public void setUsulogs(List<Logs> usulogs) {
		this.usulogs = usulogs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuid == null) ? 0 : usuid.hashCode());
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
		Usuario other = (Usuario) obj;
		if (usuid == null) {
			if (other.usuid != null)
				return false;
		} else if (!usuid.equals(other.usuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [usuid=" + usuid + ", usunome=" + usunome + ", usuemail=" + usuemail + ", usutelefone="
				+ usutelefone + "]";
	}

}

package modelo.entites;

import java.util.Date;

public class Logs {

	private Integer 		logid;
	private Date 			logdata;
	private Integer 		logusuid;
	private Usuario 		logusuario;
	
	
	public Logs(Integer logid, Date logdata, Integer logusuid, Usuario logusuario) {
		this.logid = logid;
		this.logdata = logdata;
		this.logusuid = logusuid;
		this.logusuario = logusuario;
	}

	public Logs() {
		
	}

	public Integer getLogid() {
		return logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	public Date getLogdata() {
		return logdata;
	}

	public void setLogdata(Date logdata) {
		this.logdata = logdata;
	}

	public Integer getLogusuid() {
		return logusuid;
	}

	public void setLogusuid(Integer logusuid) {
		this.logusuid = logusuid;
	}

	public Usuario getLogusuario() {
		return logusuario;
	}

	public void setLogusuario(Usuario logusuario) {
		this.logusuario = logusuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logid == null) ? 0 : logid.hashCode());
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
		Logs other = (Logs) obj;
		if (logid == null) {
			if (other.logid != null)
				return false;
		} else if (!logid.equals(other.logid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logs [logid=" + logid + ", logdata=" + logdata + ", logusuid=" + logusuid + "]";
	}
	
}

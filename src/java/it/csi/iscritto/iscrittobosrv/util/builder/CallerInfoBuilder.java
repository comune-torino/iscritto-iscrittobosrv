package it.csi.iscritto.iscrittobosrv.util.builder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;

import it.csi.iscritto.iscrittobosrv.dto.UserInfo;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.filter.IrideIdAdapterFilter;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.audit.LogAuditOperations;

public class CallerInfoBuilder implements Builder<CallerInfo> {
	private CallerInfo callerInfo;

	private CallerInfoBuilder(HttpServletRequest httpServletRequest) {
		Validate.notNull(httpServletRequest);

		UserInfo currentUser = (UserInfo) httpServletRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);

		this.callerInfo = new CallerInfo();
		this.callerInfo.setIpAddress(httpServletRequest.getRemoteAddr());
		this.callerInfo.setIdApp(Constants.COMPONENT_NAME);
		this.callerInfo.setUtente(currentUser.getCodFisc());
	}

	public static CallerInfoBuilder from(HttpServletRequest httpServletRequest) {
		return new CallerInfoBuilder(httpServletRequest);
	}

	public CallerInfoBuilder operazione(LogAuditOperations operazione) {
		Validate.notNull(operazione);
		this.callerInfo.setOperazione(operazione.getOperationName());

		return this;
	}

	public CallerInfoBuilder operazione(String operazione) {
		Validate.notBlank(operazione);
		this.callerInfo.setOperazione(operazione);

		return this;
	}

	public CallerInfoBuilder oggOper(String oggOper) {
		Validate.notBlank(oggOper);
		this.callerInfo.setOggOper(oggOper);

		return this;
	}

	public CallerInfoBuilder keyOper(String keyOper) {
		this.callerInfo.setKeyOper(keyOper);

		return this;
	}

	@Override
	public CallerInfo build() {
		return SerializationUtils.clone(this.callerInfo);
	}
}

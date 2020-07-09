package com.sisdistribuidos.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.sisdistribuidos.soap.api.enviocorreo.GetEnvioCorreoRequest;
import com.sisdistribuidos.soap.api.enviocorreo.GetEnvioCorreoResponse;
import com.sisdistribuidos.soap.api.service.EnvioCorreoService;

@Endpoint
public class EnvioCorreoindicatorEndpoint {

	private static final String NAMESPACE = "http://sisdistribuidos.com/soap/api/enviocorreo";
	@Autowired
	private EnvioCorreoService service;

	@PayloadRoot(namespace = NAMESPACE, localPart = "getEnvioCorreoRequest")
	@ResponsePayload
	public GetEnvioCorreoResponse getRespuesta(@RequestPayload GetEnvioCorreoRequest request) {
		return service.checkLoanEligibility(request);
	}

	
}

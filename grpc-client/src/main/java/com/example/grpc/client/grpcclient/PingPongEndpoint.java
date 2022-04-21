package com.example.grpc.client.grpcclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class PingPongEndpoint {    

	GRPCClientService grpcClientService;    
	@Autowired
	public PingPongEndpoint(GRPCClientService grpcClientService) {
        	this.grpcClientService = grpcClientService;
    	}

	@GetMapping("/ping")
	public String ping() {
		return grpcClientService.ping();
	}
	@GetMapping("/add")
	public String add() {
		return grpcClientService.add();
	}
    @GetMapping("/multiply")
	public String multiply() {
		return grpcClientService.multiply();
	}

	@PostMapping("/")
	public String matAddOrMultiply(@RequestParam("matrix1") MultipartFile matrix1,
								   @RequestParam("matrix2") MultipartFile matrix2,
								   @RequestParam("operation") String operation,
								   RedirectAttributes redirectAttributes) throws IOException, BadMatrixException {
		redirectAttributes.addFlashAttribute("message", "Successfully uploaded " + matrix1.getOriginalFilename()+
				" and " + matrix2.getOriginalFilename());
		String str1 = new String(matrix1.getBytes());
		String str2 = new String(matrix2.getBytes());
		return grpcClientService.matAddOrMultiply(str1, str2, operation);
	}


}

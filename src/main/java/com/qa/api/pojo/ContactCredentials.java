package com.qa.api.pojo;

import lombok.*;;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactCredentials {
	
	private String email;
	private String password;

}

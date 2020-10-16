package com.message.controller;

import com.message.dto.MessageDto;
import com.message.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j 
@RestController
class KakaoRestController {

	@Autowired
	MessageService messageService;

	@PostMapping("/save")
	public @ResponseBody ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto )  {
		messageService.save(messageDto);
		return new ResponseEntity(messageDto.getResult(), HttpStatus.OK);
	} 
}

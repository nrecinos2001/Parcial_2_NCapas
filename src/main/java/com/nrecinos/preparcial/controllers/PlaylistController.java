package com.nrecinos.preparcial.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlist")

public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping("/{code}")
	public ResponseEntity<?> findPlaylistById(@PathVariable(name = "code") UUID code){
		Playlist playlist = playlistService.findPlaylistById(code);
		
		if(playlist == null) {
			return new ResponseEntity<>("playlist no encontrado", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Playlist> playlist = playlistService.findAll();
			
		if(playlist == null) {
			return new ResponseEntity<>("playlist vacio", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> deletePlaylistById(@PathVariable(name = "code") UUID code){
		playlistService.deletePlaylist(code);
		return new ResponseEntity<>("playlist eliminada", HttpStatus.OK);
	}
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> savePlaylist(@ModelAttribute @Valid CreatePlaylistDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
			playlistService.savePlaylist(info);
			return new ResponseEntity<>("Playlist created", HttpStatus.CREATED);
	}
}

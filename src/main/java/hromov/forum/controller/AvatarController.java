package hromov.forum.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hromov.forum.model.Avatar;
import hromov.forum.model.Login;
import hromov.forum.repo.AvatarRepository;
import hromov.forum.repo.LoginRepository;

@RestController
public class AvatarController {

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/uploadFile")
    public Avatar uploadFile(@RequestParam("loginId") Long loginId, @RequestParam("file") MultipartFile fileMultipart)
	    throws IOException {
	Login login = loginRepository.findById(loginId).get();

	Avatar avatar = new Avatar(fileMultipart, login);
	avatar.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(avatar.getId())
		.toUriString());

	return avatarRepository.save(avatar);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downlaodFile(@PathVariable String fileId) throws FileNotFoundException {
	Avatar fileMultipart = avatarRepository.findById(fileId).get();

	return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileMultipart.getFileType()))
		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileMultipart.getFileName() + "\"")
		.body(new ByteArrayResource(fileMultipart.getData()));
    }

}

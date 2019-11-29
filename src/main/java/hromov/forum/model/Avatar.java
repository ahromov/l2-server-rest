package hromov.forum.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "url")
    public String fileDownloadUri;

    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "login_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Login login;

    public Avatar() {
    }

    public Avatar(MultipartFile file, Login login) throws IOException {
	this.fileName = file.getOriginalFilename();
	this.fileType = file.getContentType();
	this.data = file.getBytes();
	this.login = login;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public String getFileType() {
	return fileType;
    }

    public void setFileType(String fileType) {
	this.fileType = fileType;
    }

    public String getFileDownloadUri() {
	return fileDownloadUri;
    }

    public void setUrl(String fileDownloadUri) {
	this.fileDownloadUri = fileDownloadUri;
    }

    public byte[] getData() {
	return data;
    }

    public void setData(byte[] data) {
	this.data = data;
    }

    public Login getLogin() {
	return login;
    }

    public void setLogin(Login login) {
	this.login = login;
    }

}

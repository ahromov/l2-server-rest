package ua.cc.lajdev.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phpbb_posts")
public class Post {

	@Id
	@Column(name = "post_id")
	@JsonIgnore
	private Integer id;

	@Column(name = "topic_id")
	private Integer topicId;

	@Column(name = "forum_id")
	private Integer forumId;

	@OneToOne
	@JoinColumn(name = "poster_id", referencedColumnName = "user_id")
	private Poster poster;

	@Column(name = "post_time")
	private Integer date;

	@Column(name = "post_subject")
	private String subject;

	public Post() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}

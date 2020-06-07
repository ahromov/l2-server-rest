package ua.cc.lajdev.forum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phpbb_topics")
public class Topic {

	@Id
	@Column(name = "topic_id")
	private Integer id;

	@Column(name = "forum_id")
	private Integer forumId;

	@OneToOne /* (optional = false) */
	@JoinColumn(name = "icon_id", referencedColumnName = "icons_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Icon icon;

	@Column(name = "topic_last_poster_name")
	private String posterName;

	@Column(name = "topic_last_post_time")
	@JsonIgnore
	private Long topicLastPostTime;

	@Transient
	private Date lastPostedDate;

	@Column(name = "topic_title")
	private String title;

	public Topic() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public Long getTopicLastPostTime() {
		return topicLastPostTime;
	}

	public void setTopicLastPostTime(Long topicLastPostTime) {
		this.topicLastPostTime = topicLastPostTime;
	}

	public Date getLastPostedDate() {
		return lastPostedDate;
	}

	@PostLoad
	public void setLastPostedDate() {
		this.lastPostedDate = new Date(this.topicLastPostTime * 1000);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

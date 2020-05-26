package ua.cc.lajdev.forum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "phpbb_topics")
public class Topic {

	@Id
	@Column(name = "topic_id")
	private Integer id;

	@Column(name = "forum_id")
	private Integer forumId;

	@OneToOne
	@JoinColumn(name = "icon_id", referencedColumnName = "icons_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Icon icon;

	@Column(name = "topic_last_poster_name")
	private String posterName;

	@Column(name = "current_date")
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

	public Date getLastPostedDate() {
		return lastPostedDate;
	}

	public void setLastPostedDate(Date lastPostedDate) {
		this.lastPostedDate = lastPostedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

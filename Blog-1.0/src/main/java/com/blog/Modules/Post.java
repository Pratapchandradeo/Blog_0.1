package com.blog.Modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "post_title",length = 200,nullable = false)
	private String title;
	
	@Column(length = 10000)
	private String content;
	
	private String image;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "Category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy ="post",cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

}
